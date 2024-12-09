package application.services.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import application.models.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtServiceSecurity {
    @Value("${spring.jwt.security-token}")
    protected String token;

    protected Algorithm setAlgorithm() {
        return Algorithm.HMAC256(token);
    }

    public String jwtGenerateToken(UserModel user) {
        try {
            return JWT.create()
                    .withIssuer("api-list")
                    .withSubject(user.getEmail())
                    .withExpiresAt(getInstant())
                    .sign(setAlgorithm());
        } catch (JWTCreationException error) {
            throw new RuntimeException();
        }
    }

    public String jwtVerifyToken(String auth) {
        try {
            return JWT.require(setAlgorithm())
                    .withIssuer("api-list")
                    .build()
                    .verify(auth)
                    .getSubject();
        } catch (JWTVerificationException error) {
            return null;
        }
    }

    protected Instant getInstant() {
        return LocalDateTime.now().plusMonths(1).toInstant(ZoneOffset.of("-03:00"));
    }


}
