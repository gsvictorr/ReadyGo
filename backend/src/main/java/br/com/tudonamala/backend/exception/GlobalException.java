package br.com.tudonamala.backend.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.tudonamala.backend.exception.auth.LoginException;
import br.com.tudonamala.backend.exception.auth.RegisterException;
import br.com.tudonamala.backend.exception.auth.TokenInvalidException;
import br.com.tudonamala.backend.exception.system.SystemException;
import br.com.tudonamala.backend.exception.travelList.TravelListLimitException;
import br.com.tudonamala.backend.exception.user.UserForgotPasswordException;
import br.com.tudonamala.backend.exception.user.UserNotFoundException;

@RestControllerAdvice
public class GlobalException {

    // AccessDeniedException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public Map<String, String> exception(AccessDeniedException ex) {
        Map<String, String> errorsMap = new HashMap<>();
        errorsMap.put("error", ex.getMessage());
        return errorsMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> exception(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put("error", error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }


    // AuthenticationException
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthenticationException.class)
    public Map<String, String> exception(AuthenticationException ex) {
        Map<String, String> errorsMap = new HashMap<>();
        errorsMap.put("error", ex.getMessage());
        return errorsMap;
    }

    // DisabledException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(DisabledException.class)
    public Map<String, String> exception(DisabledException ex) {
        Map<String, String> errorsMap = new HashMap<>();
        errorsMap.put("error", "O usuário está desativado.");
        return errorsMap;
    }

    // LoginException
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(LoginException.class)
    public Map<String, String> exception(LoginException ex) {
        Map<String, String> errorsMap = new HashMap<String, String>();
        errorsMap.put("error", ex.getMessage());
        return errorsMap;
    }

    // TokenInvalidException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(TokenInvalidException.class)
    public Map<String, String> exception(TokenInvalidException ex) {
        Map<String, String> errorsMap = new HashMap<String, String>();
        errorsMap.put("error", ex.getMessage());
        return errorsMap;
    }

    // RegisterException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RegisterException.class)
    public Map<String, String> exception(RegisterException ex) {
        Map<String, String> errorsMap = new HashMap<String, String>();
        errorsMap.put("error", ex.getMessage());
        return errorsMap;
    }

    // UserNotFoundException
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> exception(UserNotFoundException ex) {
        Map<String, String> errorsMap = new HashMap<String, String>();
        errorsMap.put("error", ex.getMessage());
        return errorsMap;
    }

    // UserForgotPasswordException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserForgotPasswordException.class)
    public Map<String, String> exception(UserForgotPasswordException ex) {
        Map<String, String> errorsMap = new HashMap<String, String>();
        errorsMap.put("error", ex.getMessage());
        return errorsMap;
    }

    // TravelListLimitException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TravelListLimitException.class)
    public Map<String, String> exception(TravelListLimitException ex) {
        Map<String, String> errorsMap = new HashMap<String, String>();
        errorsMap.put("error", ex.getMessage());
        return errorsMap;
    }

    // SystemException
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SystemException.class)
    public Map<String, String> exception(SystemException ex) {
        Map<String, String> errorsMap = new HashMap<String, String>();
        errorsMap.put("error", ex.getMessage());
        return errorsMap;
    }

}
