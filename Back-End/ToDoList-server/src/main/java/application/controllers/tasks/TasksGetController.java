package application.controllers.tasks;

import application.exceptions.IncorrectCredentials;
import application.exceptions.NotFoundDataException;
import application.models.StateByTask;
import application.models.TaskModel;
import application.models.UserModel;
import application.models.repositories.TaskRepository;
import application.models.repositories.UserRepository;
import application.services.security.SecurityContextUserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("tasks")
public class TasksGetController {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/source/{id}")
    public ResponseEntity<TaskModel> geTaskById(
            @PathVariable(value = "id") UUID id

    ) {
        try {
            UserModel userModel = SecurityContextUserHolder.securityUserHolder();
            TaskModel task = taskRepository.findById(id).orElseThrow(NotFoundDataException::new);
            if (!Objects.equals(task.getUser().getEmail(), userModel.getEmail()))
                throw new IncorrectCredentials("error");
            return ResponseEntity.ok().body(task);
        } catch (NotFoundDataException | IncorrectCredentials error) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/source")
    public ResponseEntity<Page<TaskModel>> getTasks(
            @RequestParam(value = "title", defaultValue = "") String title,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "state", defaultValue = "0") int state
    ) {
        try {
            Page<TaskModel> task;
            PageRequest pagination = PageRequest.of(page, pageSize); // Use pageSize instead of offSet

            UserModel userModel = SecurityContextUserHolder.securityUserHolder();
            UUID id = userRepository.findByEmail(userModel.getEmail()).orElseThrow(NotFoundDataException::new).getId();

            if (title.isEmpty() && (state < 1 || state > 3))
                task = taskRepository.findAllByUserId(id, pagination);
            else if (title.isEmpty())
                task = taskRepository.findByUserIdAndState(id, StateByTask.fromValue(state), pagination);
            else
                task = taskRepository.findByUserIdAndTitleContaining(id, title, pagination);

            return ResponseEntity.ok().body(task);
        } catch (Exception error) {
            return ResponseEntity.notFound().build();
        }
    }
}
