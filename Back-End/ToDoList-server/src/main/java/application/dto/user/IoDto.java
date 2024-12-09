package application.dto.user;

import jakarta.validation.constraints.NotNull;

public record IoDto(
        @NotNull String email,
        @NotNull String password
) {
}
