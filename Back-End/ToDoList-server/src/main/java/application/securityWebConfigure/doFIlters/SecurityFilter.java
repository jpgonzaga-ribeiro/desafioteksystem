package application.securityWebConfigure.doFIlters;


import application.models.UserModel;
import application.models.repositories.UserRepository;
import application.services.security.JwtServiceSecurity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    JwtServiceSecurity jwtServiceSecurity;
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Authorization");
        response.setHeader("Access-Control-Expose-Headers", "Location");

        String token = recoverToken(request);
        if (token != null) {
            try {
                String jwt = jwtServiceSecurity.jwtVerifyToken(token);
                UserModel userDetails = userRepository.findByEmail(jwt).orElseThrow();
                var user = new UsernamePasswordAuthenticationToken(userDetails, null, null);
                SecurityContextHolder.getContext().setAuthentication(user);
            } catch (Exception error) {
                System.out.println("Not Found");
            }
        }
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);

        } else {
            filterChain.doFilter(request, response);
        }

    }

    protected String recoverToken(HttpServletRequest servletRequest) {
        var authorization = servletRequest.getHeader("Authorization");
        if (authorization == null) return null;
        return authorization.replace("Bearer", "").replace(" ", "");
    }
}
