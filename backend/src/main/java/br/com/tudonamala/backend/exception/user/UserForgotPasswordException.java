package br.com.tudonamala.backend.exception.user;


public class UserForgotPasswordException extends RuntimeException {
    public UserForgotPasswordException(String error) {
        super(error);
    }
}