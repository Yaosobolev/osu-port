package com.osu.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.osu.backend.model.ship.Ship;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class RequestDto {
    private Long id;

    private LocalDateTime arrival;
    private LocalDateTime newArrival;

    private Integer day_of_stay;

    private Ship ship;

    public RequestDto( Long id, LocalDateTime arrival) {
        this.id = id;
        this.arrival = arrival;
    }
    public RequestDto() {}



    public Integer getDay_of_stay() {
        return day_of_stay;
    }

    public void setDay_of_stay(Integer day_of_stay) {
        this.day_of_stay = day_of_stay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public LocalDateTime getNewArrival() {
        return newArrival;
    }

    public void setNewArrival(LocalDateTime newArrival) {
        this.newArrival = newArrival;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
