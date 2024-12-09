package application.services.security;

import application.models.UserModel;
import org.springframework.security.core.context.SecurityContextHolder;

public interface SecurityContextUserHolder {
    static UserModel securityUserHolder() {
        return (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
}
