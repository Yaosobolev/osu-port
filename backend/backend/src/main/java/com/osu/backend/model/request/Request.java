package com.osu.backend.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.osu.backend.model.cargo.Cargo;
import com.osu.backend.model.crane.Crane;
import com.osu.backend.model.ship.Ship;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "arrival_time")
    private String arrival_time;

    @Column(name = "day_of_stay")
    private String day_of_stay;

    @ManyToOne
    @JoinColumn(name = "ship")
    public Ship ship;

//    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
//    private List<Crane> crane;

//222
//    @ManyToOne
//    @JoinColumn(name = "crane")
//    private Crane crane;

    @ManyToOne
    @JoinColumn(name = "crane")
    @JsonIgnore
    private Crane crane;

//    @OneToMany(mappedBy = "request")
//    private List<Ship> ship;

    @Column(name = "status")
    private String status;

    public Request(){}
    public Request(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getDay_of_stay() {
        return day_of_stay;
    }

    public void setDay_of_stay(String day_of_stay) {
        this.day_of_stay = day_of_stay;
    }

//    public Ship getShip() {
//        return ship;
//    }
//
//    public void setShip(Ship ship) {
//        this.ship = ship;
//    }

//    public List<Crane> getCrane() {
//        return crane;
//    }
//
//    public void setCrane(List<Crane> crane) {
//        this.crane = crane;
//    }

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

//    public List<Ship> getShip() {
//        return ship;
//    }
//
//    public void setShip(List<Ship> ship) {
//        this.ship = ship;
//    }


    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}