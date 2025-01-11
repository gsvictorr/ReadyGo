package br.com.tudonamala.backend.exception.sharedAccess;

public class SharedAccessException extends RuntimeException {
    public SharedAccessException(String error) {
        super(error);
    }
}