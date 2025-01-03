package br.com.tudonamala.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.tudonamala.backend.config.security.UserRole;
import br.com.tudonamala.backend.dto.auth.register.RegisterRequest;
import br.com.tudonamala.backend.dto.auth.user.UserResponse;
import br.com.tudonamala.backend.exception.auth.RegisterException;
import br.com.tudonamala.backend.model.User;
import br.com.tudonamala.backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse createUser(RegisterRequest registerRequest) {

        if (userRepository.findByEmail(registerRequest.email()) != null) {
            throw new RegisterException("Esse email já está cadastrado em outro usuário.");
        }

        try {
            User newUser = registerRequest.convert(registerRequest);
            String password = passwordEncoder.encode(newUser.getPassword());
            newUser.setPassword(password);
            newUser.setRole(UserRole.USER);
            userRepository.save(newUser);

            UserResponse userResponse = new UserResponse(newUser);
            return userResponse;

        } catch (Exception e) {
            throw new RegisterException("Ocorreu um erro ao registrar o usuário: " + e.getMessage());
        }
    }

}
