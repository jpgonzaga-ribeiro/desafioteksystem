package application.models;

import lombok.Getter;

@Getter
public enum StateByTask {
    UNFULFILLED(1),
    DONE(2),
    DELETED(3);

    private final int value;

    StateByTask(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static StateByTask fromValue(int value) {
        for (StateByTask state : StateByTask.values()) {
            if (state.getValue() == value) {
                return state;
            }
        }
        return fromValue(1);
    }
}