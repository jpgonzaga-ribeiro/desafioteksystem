package application.dto.user;


import jakarta.validation.constraints.NotNull;

public record DeleteDto(
        @NotNull String password) {
}
