package auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.google.gson.Gson;
import model.TokenResp;
import model.User;
import service.IUserService;
import utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Inject
    private IUserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        User newUser = gson.fromJson(HttpUtil.of(request.getReader()).getValue(), User.class);

        if (userService.findOne(newUser.getUsername()) != null) {
            TokenResp tokenResp = new TokenResp(false, "Username already exists");
            out.write(gson.toJson(tokenResp));
            return;
        }
        newUser.setRankId(1);
        newUser = userService.register(newUser);
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            Map<String, String> claims = new HashMap<>();
            claims.put("id", String.valueOf(newUser.getId()));
            claims.put("username", newUser.getUsername());
            TemporalAmount TOKEN_VALIDITY = Duration.ofMinutes(5L);
            String token = JWT.create()
                    .withClaim("user", claims)
                    .withIssuer("auth0")
                    .sign(algorithm);
            TokenResp tokenResp = new TokenResp(true, "Register successfully", token);
            response.setStatus(200);
            out.write(gson.toJson(tokenResp));
        } catch (JWTCreationException exception){
//                        .withExpiresAt(Date.from(Instant.now().plus(TOKEN_VALIDITY)))
            //Invalid Signing configuration / Couldn't convert Claims.
        }
    }
}
