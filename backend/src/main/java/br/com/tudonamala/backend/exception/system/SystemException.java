package br.com.tudonamala.backend.exception.system;

public class SystemException extends RuntimeException {
    public SystemException(String error) {
        super(error);
    }
}