package br.com.tudonamala.backend.dto.auth.user;

import br.com.tudonamala.backend.config.security.UserRole;
import br.com.tudonamala.backend.model.User;

public record UserResponse (Long id, String name, String email, boolean passwordChanged, UserRole role, boolean isEnabled){
    
    public UserResponse(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.isPasswordChanged(), user.getRole(), user.isEnabled());
    }
}