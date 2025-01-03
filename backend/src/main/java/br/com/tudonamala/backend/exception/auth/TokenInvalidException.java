package br.com.tudonamala.backend.exception.auth;

public class TokenInvalidException extends RuntimeException {
    public TokenInvalidException(String error) {
        super(error);
    }
}
