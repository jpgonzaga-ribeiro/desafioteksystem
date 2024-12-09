package application.models.listener;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class DataListener {
    @Column(name = "data_create")
    protected LocalDateTime dataCreate;
    @Column(name = "data_update")
    protected LocalDateTime dataUpdate;

    @PrePersist
    public void setDataCreate() {
        dataCreate = LocalDateTime.now();
    }

    @PreUpdate
    public void setDataUpdate() {
        dataUpdate = LocalDateTime.now();
    }
}
