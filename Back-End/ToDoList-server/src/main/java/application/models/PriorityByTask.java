package application.models;

import lombok.Getter;

@Getter
public enum PriorityByTask {
    MAX(1),
    AVERAGE(2),
    DELETED(3);

    private final int value;

    PriorityByTask(int value) {
        this.value = value;
    }

    public static PriorityByTask fromValue(int value) {
        for (PriorityByTask priority : PriorityByTask.values()) {
            if (priority.getValue() == value) {
                return priority;
            }
        }
        throw new IllegalArgumentException("Invalid value for StateByTask: " + value);
    }
}
