package application.dto.task;

import jakarta.validation.constraints.NotNull;

public record UpdateTitleAndDescriptionDto(@NotNull String title, @NotNull String description) {
}
