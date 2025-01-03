package br.com.tudonamala.backend.exception.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String error) {
        super(error);
    }
}