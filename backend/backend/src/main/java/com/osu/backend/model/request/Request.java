package com.osu.backend.model.request;

import com.osu.backend.model.cargo.Cargo;
import com.osu.backend.model.ship.Ship;
import jakarta.persistence.*;

@Entity
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "arrival_time")
    private String arrival_time;

    @ManyToOne
    @JoinColumn(name = "ship_id")
    public Ship ship_id;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    public Cargo cargo_id;

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

    public Ship getShip_id() {
        return ship_id;
    }

    public void setShip_id(Ship ship_id) {
        this.ship_id = ship_id;
    }

    public Cargo getCargo_id() {
        return cargo_id;
    }

    public void setCargo_id(Cargo cargo_id) {
        this.cargo_id = cargo_id;
    }
}