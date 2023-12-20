package com.osu.backend.exception;

public class CargoNotFoundException extends RuntimeException{

    public CargoNotFoundException(Long id){
        super("Товар с таким id не найден: " + id);
    }
}
