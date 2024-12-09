package application.dto.user;

import jakarta.validation.constraints.NotNull;

public record UpdatePasswordDto(
        @NotNull String password,
        @NotNull String passwordNew,
        @NotNull String username

) {

}
