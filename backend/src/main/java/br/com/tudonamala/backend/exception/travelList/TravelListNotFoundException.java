package br.com.tudonamala.backend.exception.travelList;


public class TravelListNotFoundException extends RuntimeException {
    public TravelListNotFoundException(String error) {
        super(error);
    }
}
