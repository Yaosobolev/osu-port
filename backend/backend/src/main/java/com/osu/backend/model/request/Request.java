package com.osu.backend.model.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.osu.backend.model.cargo.Cargo;
import com.osu.backend.model.crane.Crane;
import com.osu.backend.model.schedule.Schedule;
import com.osu.backend.model.ship.Ship;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "arrival_time")
    private LocalDateTime arrival_time;

    @Column(name = "new_arrival_time")
    private LocalDateTime new_arrival_time;


    @Column(name = "serving")
    private LocalDateTime serving;

    @Column(name = "day_of_stay")
    private Integer day_of_stay;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ship")
    public Ship ship;


    @ManyToOne
    @JoinColumn(name = "crane")
//    @JsonIgnore
    private Crane crane;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule")
    @JsonBackReference
    private Schedule schedule;

    public Request(){}



    public Request(Long id, LocalDateTime arrival_time, LocalDateTime new_arrival_time, LocalDateTime serving, Integer day_of_stay, Ship ship, Crane crane, String status, Schedule schedule) {
        this.id = id;
        this.arrival_time = arrival_time;
        this.new_arrival_time = new_arrival_time;
        this.day_of_stay = day_of_stay;
        this.ship = ship;
        this.crane = crane;
        this.status = status;
        this.schedule = schedule;
        this.serving = serving;
    }


    public LocalDateTime getNew_arrival_time() {
        return new_arrival_time;
    }

    public void setNew_arrival_time(LocalDateTime new_arrival_time) {
        this.new_arrival_time = new_arrival_time;
    }

    public Request(Long id) {
        this.id = id;
    }

    public LocalDateTime getServing() {
        return serving;
    }

    public void setServing(LocalDateTime serving) {
        this.serving = serving;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LocalDateTime getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(LocalDateTime arrival_time) {
        this.arrival_time = arrival_time;
    }

    public Integer getDay_of_stay() {
        return day_of_stay;
    }

    public void setDay_of_stay(Integer day_of_stay) {
        this.day_of_stay = day_of_stay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Crane getCrane() {
        return crane;
    }

    public void setCrane(Crane crane) {
        this.crane = crane;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }



}

