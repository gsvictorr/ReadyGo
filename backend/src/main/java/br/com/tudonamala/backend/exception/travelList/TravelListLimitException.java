package br.com.tudonamala.backend.exception.travelList;

public class TravelListLimitException extends RuntimeException {
    public TravelListLimitException(String error) {
        super(error);
    }
}
