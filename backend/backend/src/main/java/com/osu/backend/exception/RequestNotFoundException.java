package com.osu.backend.exception;

public class RequestNotFoundException extends RuntimeException {
    public RequestNotFoundException(Long id){
        super("Заявка с таким id не найден: " + id);
    }
}
