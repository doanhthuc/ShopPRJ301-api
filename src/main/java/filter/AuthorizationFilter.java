package filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.google.gson.Gson;
import model.TokenResp;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getMethod().equals("POST")
                && req.getServletPath().equals("/LoginServlet")) {
            chain.doFilter(req, response);
        } else if (req.getServletPath().equals("/RegisterServlet")) {
            chain.doFilter(req, response);
        }else if (req.getMethod().equals("GET")
                && req.getServletPath().equals("/ProductServlet")) {
            chain.doFilter(req, response);
        } else {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            String authHeader = req.getHeader("Authorization");
            String token = null;
            if (authHeader != null) {
                token = authHeader.substring("Bearer ".length());
            }
            if (token == null) {
                TokenResp tokenResp = new TokenResp(false, "Access token not found");
                out.write(gson.toJson(tokenResp));
                return;
            }
            try {
                Algorithm algorithm = Algorithm.HMAC256("secret");
                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer("auth0")
                        .build(); //Reusable verifier instance
                DecodedJWT jwt = verifier.verify(token);
                Claim claim = jwt.getClaim("user");
                Map<String, Object> map = claim.asMap();
                System.out.println(map.toString());
                req.setAttribute("userId", map.get("id"));
                req.setAttribute("username", map.get("username"));
                chain.doFilter(req, response);
            } catch (JWTVerificationException exception){
                //Invalid signature/claims
                TokenResp tokenResp = new TokenResp(false, "Invalid Access token");
                out.write(gson.toJson(tokenResp));
            }
        }
    }
}
