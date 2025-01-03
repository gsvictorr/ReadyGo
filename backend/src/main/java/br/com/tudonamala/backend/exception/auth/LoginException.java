package br.com.tudonamala.backend.exception.auth;

public class LoginException extends RuntimeException {
    public LoginException(String error) {
        super(error);
    }
}
