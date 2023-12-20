package com.osu.backend.model.schedule;

import com.osu.backend.model.cargo.Cargo;
import com.osu.backend.model.crane.Crane;
import com.osu.backend.model.request.Request;
import com.osu.backend.model.ship.Ship;
import jakarta.persistence.*;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ship_id")
    public Ship ship_id;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    public Cargo cargo_id;

    @ManyToOne
    @JoinColumn(name = "crane_id")
    public Crane crane_id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    public Request request_id;

    @Column(name = "status")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Crane getCrane_id() {
        return crane_id;
    }

    public void setCrane_id(Crane crane_id) {
        this.crane_id = crane_id;
    }

    public Request getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Request request_id) {
        this.request_id = request_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}