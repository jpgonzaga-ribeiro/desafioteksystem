package application.services.controller.models;

import application.exceptions.NotFoundDataException;
import application.models.TaskModel;
import application.models.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;


    public TaskModel updateTaskTitleAndDescription(UUID taskId, String newTitle, String newDescription) {
        TaskModel task = taskRepository.findById(taskId).orElseThrow(NotFoundDataException::new);
        task.setTitle(newTitle);
        task.setDescription(newDescription);
        return taskRepository.save(task);
    }

}
