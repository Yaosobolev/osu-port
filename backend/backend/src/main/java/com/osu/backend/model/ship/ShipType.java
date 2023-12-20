package com.osu.backend.model.ship;

import jakarta.persistence.*;

@Entity
@Table(name = "ship_type")
public class ShipType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;


    public ShipType() {
    }

    public ShipType(Long id) {
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
}

