package br.com.tudonamala.backend.exception.auth;

public class RegisterException extends RuntimeException {
    public RegisterException(String error) {
        super(error);
    }
}
