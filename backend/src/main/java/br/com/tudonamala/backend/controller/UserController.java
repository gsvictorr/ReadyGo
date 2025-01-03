package br.com.tudonamala.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tudonamala.backend.dto.auth.register.RegisterRequest;
import br.com.tudonamala.backend.dto.auth.user.UserResponse;
import br.com.tudonamala.backend.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid RegisterRequest registerRequest) {
        UserResponse userResponse = userService.createUser(registerRequest);
        return ResponseEntity.ok().body(userResponse);
    }

}
