package application.controllers.tasks;

import application.exceptions.IncorrectCredentials;
import application.exceptions.NotFoundDataException;
import application.models.TaskModel;
import application.models.UserModel;
import application.models.repositories.TaskRepository;
import application.models.repositories.UserRepository;
import application.dto.task.TaskDto;
import application.services.security.SecurityContextUserHolder;
import jakarta.persistence.PersistenceException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("tasks")
public class TaskPostController {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("")
    protected ResponseEntity<Object> createTask(@RequestBody @Valid TaskDto taskDto) {
        try {
            UserModel userContext = SecurityContextUserHolder.securityUserHolder();
            System.out.println(SecurityContextUserHolder.securityUserHolder());
            UserModel user = userRepository.findByEmail(userContext.getEmail()).orElseThrow(NotFoundDataException::new);

            TaskModel taskModel = new TaskModel();

            BeanUtils.copyProperties(taskDto, taskModel);

            taskModel.setPriority(taskDto.priority());
            taskModel.setUser(user);

            return ResponseEntity.status(HttpStatus.CREATED).body(taskRepository.save(taskModel));

        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error.getMessage());
        }
    }


}
