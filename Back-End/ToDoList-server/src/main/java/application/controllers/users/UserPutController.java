package application.controllers.users;

import application.dto.user.UpdatePasswordDto;
import application.models.UserModel;
import application.models.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class UserPutController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;


    @PutMapping("/update")
    protected ResponseEntity<Object> updateUser(@RequestBody @Valid UpdatePasswordDto updateDto) {
        try {
            String email = ((UserModel) SecurityContextHolder.getContext().getAuthentication().getDetails()).getEmail();

            var token = new UsernamePasswordAuthenticationToken(email, updateDto.password());

            authenticationManager.authenticate(token);

            UserModel userModel = userRepository.findByEmail(email).get();

            userModel.setPassword(updateDto.passwordNew());
            userModel.setUsername(updateDto.username());
            userRepository.save(userModel);
            return ResponseEntity.ok().build();
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Credential incorrect");
        }
    }


}

