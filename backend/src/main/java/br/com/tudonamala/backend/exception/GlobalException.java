package br.com.tudonamala.backend.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.tudonamala.backend.exception.auth.LoginException;
import br.com.tudonamala.backend.exception.auth.RegisterException;
import br.com.tudonamala.backend.exception.auth.TokenInvalidException;
import br.com.tudonamala.backend.exception.user.UserNotFoundException;

@RestControllerAdvice
public class GlobalException {

    // AccessDeniedException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public Map<String, String> acessDeniedException(AccessDeniedException ex) {
        Map<String, String> errorsMap = new HashMap<>();
        errorsMap.put("error", ex.getMessage());
        return errorsMap;
    }

    // AuthenticationException
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthenticationException.class)
    public Map<String, String> acessDeniedException(AuthenticationException ex) {
        Map<String, String> errorsMap = new HashMap<>();
        errorsMap.put("error", ex.getMessage());
        return errorsMap;
    }

    // DisabledException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(DisabledException.class)
    public Map<String, String> disabledException(DisabledException ex) {
        Map<String, String> errorsMap = new HashMap<>();
        errorsMap.put("error", "O usuário está desativado.");
        return errorsMap;
    }

    // LoginException
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(LoginException.class)
    public Map<String, String> loginException(LoginException ex) {
        Map<String, String> errorsMap = new HashMap<String, String>();
        errorsMap.put("error", ex.getMessage());
        return errorsMap;
    }

    // TokenInvalidException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(TokenInvalidException.class)
    public Map<String, String> tokenInvalidException(TokenInvalidException ex) {
        Map<String, String> errorsMap = new HashMap<String, String>();
        errorsMap.put("error", ex.getMessage());
        return errorsMap;
    }

    // RegisterException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RegisterException.class)
    public Map<String, String> registerException(RegisterException ex) {
        Map<String, String> errorsMap = new HashMap<String, String>();
        errorsMap.put("error", ex.getMessage());
        return errorsMap;
    }
    
    // UserNotFoundException
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> userNotFoundException(UserNotFoundException ex) {
        Map<String, String> errorsMap = new HashMap<String, String>();
        errorsMap.put("error", ex.getMessage());
        return errorsMap;
    }
}
