package br.com.tudonamala.backend.exception.listItem;


public class ListItemLimitException extends RuntimeException {
    public ListItemLimitException(String error) {
        super(error);
    }
}