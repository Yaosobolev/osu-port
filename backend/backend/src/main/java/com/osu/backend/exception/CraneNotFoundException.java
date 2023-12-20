package com.osu.backend.exception;

public class CraneNotFoundException extends RuntimeException{
    public CraneNotFoundException(Long id){
        super("Кран с таким id не найден: " + id);
    }
}
