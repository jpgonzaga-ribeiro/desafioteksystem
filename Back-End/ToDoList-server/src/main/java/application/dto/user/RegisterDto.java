package application.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterDto(
        @NotNull String email,
        @NotNull String username,
        @NotNull String password
) {
}
