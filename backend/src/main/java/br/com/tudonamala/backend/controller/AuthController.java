package br.com.tudonamala.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tudonamala.backend.dto.auth.AuthRequest;
import br.com.tudonamala.backend.dto.auth.AuthRespose;
import br.com.tudonamala.backend.dto.auth.token.TokenValidateRequest;
import br.com.tudonamala.backend.dto.auth.token.TokenValidateResponse;
import br.com.tudonamala.backend.exception.user.UserNotFoundException;
import br.com.tudonamala.backend.model.User;
import br.com.tudonamala.backend.repository.UserRepository;
import br.com.tudonamala.backend.service.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthRespose> login(@RequestBody @Valid AuthRequest authRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authRequest.email(),
                authRequest.password());

        var authentication = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new AuthRespose(token));
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenValidateResponse> validateToken(
            @RequestBody @Valid TokenValidateRequest tokenValidateRequest) {

        var outputValid = tokenService.validateToken(tokenValidateRequest.token());

        var isValid = false;

        if (!outputValid.isBlank()) {
            isValid = true;
        }

        Long userId = tokenService.getUserFromToken(tokenValidateRequest.token());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com o ID fornecido."));

        return ResponseEntity.ok().body(new TokenValidateResponse(isValid, user.getRole()));

    }

}
