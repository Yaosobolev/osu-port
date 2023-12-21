package com.osu.backend.model.cargo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.osu.backend.model.crane.CraneType;
import com.osu.backend.model.ship.Ship;
import com.osu.backend.model.ship.ShipType;
import jakarta.persistence.*;

@Entity
@Table(name = "cargo")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Long id;

//    @Column(name = "name")
    private String name;

//    @Column(name = "value")
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "cargo_type")
    private CargoType cargo_type;

    @ManyToOne
    @JsonIgnore
    private Ship ship;

    public Cargo() {
    }

    public Cargo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public CargoType getCargo_type() {
        return cargo_type;
    }

    public void setCargo_type(CargoType cargo_type) {
        this.cargo_type = cargo_type;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
        if (ship != null && !ship.getCargo().contains(this)) {
            ship.getCargo().add(this);
        }


    }

}