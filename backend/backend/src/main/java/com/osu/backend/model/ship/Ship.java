package com.osu.backend.model.ship;

import com.osu.backend.model.cargo.CargoType;
import jakarta.persistence.*;

@Entity
@Table(name = "ship")
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "day_of_stay")
    private Integer day_of_stay;

    @ManyToOne
    @JoinColumn(name = "ship_type")
    public ShipType ship_type;

    @Column(name = "status")
    private String status;

    public Ship(){}

    public Ship(Long id) {
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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getDay_of_stay() {
        return day_of_stay;
    }

    public void setDay_of_stay(Integer day_of_stay) {
        this.day_of_stay = day_of_stay;
    }

    public ShipType getShip_type() {
        return ship_type;
    }

    public void setShip_type(ShipType ship_type) {
        this.ship_type = ship_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}