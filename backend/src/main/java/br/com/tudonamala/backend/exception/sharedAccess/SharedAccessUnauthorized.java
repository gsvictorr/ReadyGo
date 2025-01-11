package br.com.tudonamala.backend.exception.sharedAccess;


public class SharedAccessUnauthorized extends RuntimeException {
    public SharedAccessUnauthorized(String error) {
        super(error);
    }
}
