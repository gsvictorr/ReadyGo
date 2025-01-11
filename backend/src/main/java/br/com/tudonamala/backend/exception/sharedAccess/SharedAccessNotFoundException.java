package br.com.tudonamala.backend.exception.sharedAccess;

public class SharedAccessNotFoundException extends RuntimeException {
    public SharedAccessNotFoundException(String error) {
        super(error);
    }
}