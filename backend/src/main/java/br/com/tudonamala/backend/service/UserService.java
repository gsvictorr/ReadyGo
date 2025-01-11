package br.com.tudonamala.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.tudonamala.backend.config.security.UserRole;
import br.com.tudonamala.backend.dto.auth.register.RegisterRequest;
import br.com.tudonamala.backend.dto.auth.user.UserForgotPasswordRequest;
import br.com.tudonamala.backend.dto.auth.user.UserResponse;
import br.com.tudonamala.backend.exception.auth.RegisterException;
import br.com.tudonamala.backend.exception.user.UserForgotPasswordException;
import br.com.tudonamala.backend.exception.user.UserNotFoundException;
import br.com.tudonamala.backend.model.User;
import br.com.tudonamala.backend.repository.UserRepository;
import br.com.tudonamala.backend.utils.RandomCode;

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
            newUser.setCreatedAt(LocalDateTime.now());
            userRepository.save(newUser);

            return new UserResponse(newUser);

        } catch (Exception e) {
            throw new RegisterException("Ocorreu um erro ao registrar o usuário: " + e.getMessage());
        }
    }

    public UserResponse getUser(Long id) {

        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            UserResponse userResponse = new UserResponse(user.get().getId(), user.get().getName(),
                    user.get().getEmail(),
                    user.get().isPasswordChanged(), user.get().getRole(),
                    user.get().isEnabled());

            return userResponse;
        } else {
            throw new UserNotFoundException("Não foi possível encontrar esse usuário.");
        }

    }

    public List<UserResponse> listAll() {
        List<UserResponse> users = userRepository.findAll().stream().map(UserResponse::new).toList();
        return users;
    }

    public UserResponse forgotPassword(UserForgotPasswordRequest userForgotPasswordRequest) {

        User user = (User) userRepository.findByEmail(userForgotPasswordRequest.email());

        if (user == null) {
            throw new UserNotFoundException("Não foi possível encontrar esse usuário.");
        }

        try {
            String code = RandomCode.generateRandomString(16);
            String encodedPassword = passwordEncoder.encode(code);
            user.setPassword(encodedPassword);
            user.setPasswordChanged(false);
            userRepository.save(user);

            // continuar com o envio do email

            UserResponse userResponse = new UserResponse(user);
            return userResponse;

        } catch (Exception e) {
            throw new UserForgotPasswordException(
                    "Ocorreu um erro ao utilizar o 'esqueceu sua senha?' :" + e.getMessage());
        }
    }

}
