package application.models;

import application.models.listener.DataListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "task_model")
public class TaskModel extends DataListener implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(length = 50, nullable = false, updatable = true)
    protected String title;

    @Column(length = 50, nullable = false, updatable = true)
    protected String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    protected UserModel user;

    protected StateByTask state;

    protected PriorityByTask priority;

    public void setPriority(int value) {
        priority = PriorityByTask.fromValue(value);
    }

    @PrePersist
    public void setStateDefault() {
        state = StateByTask.fromValue(1);
    }

    public void setState(int stateValue) {
        state = StateByTask.fromValue(stateValue);
    }

    public TaskModel(UUID id, String title, String description, UserModel user, StateByTask state, PriorityByTask priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
        this.state = state;
        this.priority = priority;
    }

    public TaskModel() {
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public StateByTask getState() {
        return state;
    }

    public void setState(StateByTask state) {
        this.state = state;
    }

    public PriorityByTask getPriority() {
        return priority;
    }

    public void setPriority(PriorityByTask priority) {
        this.priority = priority;
    }
}
