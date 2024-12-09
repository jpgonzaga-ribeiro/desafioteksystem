package application.services.controller.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class TokenHash {
    protected String token;
}
