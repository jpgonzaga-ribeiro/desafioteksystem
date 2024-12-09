package application.controllers.users;


import application.dto.user.DeleteDto;
import application.models.UserModel;
import application.models.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class UserDeleteController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @DeleteMapping("/delete")
    protected ResponseEntity deleteUser(@RequestBody @Valid DeleteDto deleteDto) {
        try {
            String email = ((UserModel) SecurityContextHolder.getContext().getAuthentication().getDetails()).getEmail();
            var token = new UsernamePasswordAuthenticationToken(email, deleteDto.password());
            authenticationManager.authenticate(token);
            userRepository.delete(userRepository.findByEmail(email).get());
            return ResponseEntity.ok().build();
        } catch (Exception error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }
}
