package application.services.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderBASE64 {
    public static String passwordEncoder(String pass) {
        return new BCryptPasswordEncoder().encode(pass);
    }
}
