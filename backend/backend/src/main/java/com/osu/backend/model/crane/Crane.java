package com.osu.backend.model.crane;

import com.osu.backend.model.cargo.Cargo;
import jakarta.persistence.*;

import javax.swing.*;


@Entity
@Table(name = "crane")
public class Crane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;


    @ManyToOne
    @JoinColumn(name = "crane_type")
    public CraneType crane_type;

    public Crane (){}
    public Crane(Long id) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CraneType getCrane_type() {
        return crane_type;
    }

    public void setCrane_type(CraneType crane_type) {
        this.crane_type = crane_type;
    }
}

