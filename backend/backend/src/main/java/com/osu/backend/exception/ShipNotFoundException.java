package com.osu.backend.exception;

public class ShipNotFoundException extends RuntimeException{
    public ShipNotFoundException(Long id){
        super("Судно с таким id не найден: " + id);
    }
}
