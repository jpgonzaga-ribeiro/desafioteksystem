package application.dto.task;

import jakarta.validation.constraints.NotNull;

public record TaskDto(
        @NotNull String title,
        @NotNull String description,
        @NotNull int priority
) {
}
