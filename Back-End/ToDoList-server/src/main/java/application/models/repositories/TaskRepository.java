package application.models.repositories;

import application.models.StateByTask;
import application.models.TaskModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
    Page<TaskModel> findByUserIdAndState(UUID id, StateByTask state, PageRequest page);

    Page<TaskModel> findByUserIdAndTitleContaining(UUID userId, String title, PageRequest pageRequest);

    Page<TaskModel> findAllByUserId(UUID id, PageRequest pageRequest);
}
