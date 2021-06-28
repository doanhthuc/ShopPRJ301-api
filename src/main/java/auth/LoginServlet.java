package auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
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
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Inject
    private IUserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String userId = request.getParameter("userId");
        String username = request.getParameter("username");
        User user = userService.findOne(userId);
        if (user == null) {
            response.setStatus(400);
//            response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
            TokenResp tokenResp = new TokenResp(false, null);
            out.write(gson.toJson(tokenResp));
            return;
        }
        response.setStatus(500);
//        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        out.write(gson.toJson(user));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        User user = gson.fromJson(HttpUtil.of(request.getReader()).getValue(), User.class);
        if (user.getUsername() == null || user.getPassword() == null) {
            response.setStatus(400);
            TokenResp tokenResp = new TokenResp(false, "Missing username and/or password");
            out.write(gson.toJson(tokenResp));
            return;
        }
        user = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (user == null) {
            response.setStatus(401);
            TokenResp tokenResp = new TokenResp(false, "Incorrect username or password");
            out.write(gson.toJson(tokenResp));
        } else {
            try {
                Algorithm algorithm = Algorithm.HMAC256("secret");
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", user.getId());
                claims.put("username", user.getUsername());
                TemporalAmount TOKEN_VALIDITY = Duration.ofMinutes(5L);
                String token = JWT.create()
                        .withClaim("user", claims)
                        .withIssuer("auth0")
                        .sign(algorithm);
                TokenResp tokenResp = new TokenResp(true, "Login successfully", token);
                response.setStatus(200);
//                response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
                out.write(gson.toJson(tokenResp));
            } catch (JWTCreationException exception){
//                        .withExpiresAt(Date.from(Instant.now().plus(TOKEN_VALIDITY)))
                //Invalid Signing configuration / Couldn't convert Claims.
            }

        }
    }
}
