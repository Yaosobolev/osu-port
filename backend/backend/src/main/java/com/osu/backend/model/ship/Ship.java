package com.osu.backend.model.ship;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.osu.backend.model.cargo.Cargo;
import com.osu.backend.model.cargo.CargoType;
import com.osu.backend.model.crane.Crane;
import com.osu.backend.model.request.Request;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "cargo_name")
    private String cargo_name;

    @Column(name = "valume")
    private Integer valume;

    @Column(name = "planned_stay_days")
    private Integer planned_stay_days;

    @ManyToOne
    @JoinColumn(name = "cargo_type")
    private CargoType cargo_type;

    @ManyToOne
    @JoinColumn(name = "ship_type")
    private ShipType ship_type;


    @Column(name = "status")
    private String status ;

    @OneToMany(mappedBy = "ship", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnoreProperties("ship")
    private List<Cargo> cargo = new ArrayList<>(); // Initialize the list

    public Ship(){}

    public Ship(Long id) {
        this.id = id;
    }

    public Ship(String name, Integer weight, ShipType ship_type, String cargo_name, CargoType cargo_type,Integer valume , String status) {
        this.name = name;
        this.weight = weight;
        this.ship_type = ship_type;
        this.cargo_name = cargo_name;
        this.cargo_type = cargo_type;
        this.valume = valume;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getPlanned_stay_days() {
        return planned_stay_days;
    }

    public void setPlanned_stay_days(Integer planned_stay_days) {
        this.planned_stay_days = planned_stay_days;
    }
    //    public void setPlanned_stay_days(Integer planned_stay_days) {
//        this.planned_stay_days = planned_stay_days;
//    }pLanned_stay_days

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

    public ShipType getShip_type() {
        return ship_type;
    }

    public void setShip_type(ShipType ship_type) {
        this.ship_type = ship_type;

    }

    public List<Cargo> getCargo() {
        return cargo;
    }

    public void setCargo(List<Cargo> cargo) {
        this.cargo = cargo;
    }

    public String getCargo_name() {
        return cargo_name;
    }

    public void setCargo_name(String cargo_name) {
        this.cargo_name = cargo_name;
    }

    public Integer getValume() {
        return valume;
    }

    public void setValume(Integer valume) {
        this.valume = valume;
    }

    public CargoType getCargo_type() {
        return cargo_type;
    }

    public void setCargo_type(CargoType cargo_type) {
        this.cargo_type = cargo_type;
    }
}