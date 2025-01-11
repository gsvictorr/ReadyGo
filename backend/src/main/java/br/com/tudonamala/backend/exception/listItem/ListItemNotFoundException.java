package br.com.tudonamala.backend.exception.listItem;


public class ListItemNotFoundException extends RuntimeException {
    public ListItemNotFoundException(String error) {
        super(error);
    }
}
