package application.controllers.tasks;

import application.dto.task.UpdateTitleAndDescriptionDto;
import application.exceptions.IncorrectCredentials;
import application.exceptions.NotFoundDataException;
import application.models.TaskModel;
import application.models.UserModel;
import application.models.repositories.TaskRepository;
import application.services.controller.models.TaskService;
import application.services.security.SecurityContextUserHolder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("tasks")
public class TaskPutController {
    @Autowired
    TaskService taskService;
    @Autowired
    TaskRepository taskRepository;

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskModel> updateTask(
            @PathVariable UUID taskId,
            @RequestBody @Valid UpdateTitleAndDescriptionDto updateTitleAndDescriptionDto) {
        TaskModel updatedTask = taskService.updateTaskTitleAndDescription(taskId, updateTitleAndDescriptionDto.title(), updateTitleAndDescriptionDto.description());
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/done")
    protected ResponseEntity<TaskModel> doneTask(@RequestParam(value = "key") UUID id) {
        UserModel userModel = SecurityContextUserHolder.securityUserHolder();
        try {
            TaskModel taskModel = taskRepository.findById(id).orElseThrow(NotFoundDataException::new);
            if (!Objects.equals(taskModel.getUser().getEmail(), userModel.getEmail())) {
                throw new IncorrectCredentials("Incorrect Credentials");
            }
            ;
            taskModel.setState(2);
            return ResponseEntity.ok().body(taskRepository.save(taskModel));
        } catch (NotFoundDataException | IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
