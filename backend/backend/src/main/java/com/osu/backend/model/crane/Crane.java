package com.osu.backend.model.crane;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.osu.backend.model.cargo.Cargo;
import com.osu.backend.model.request.Request;
import com.osu.backend.model.ship.Ship;
import jakarta.persistence.*;

import javax.swing.*;
import java.util.List;


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

    @OneToMany(mappedBy = "crane", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Request> request;

    @Column(name = "workload")
    private Integer workload;


    public Crane (){}
    public Crane(Long id) {
        this.id = id;
    }

    public Crane(Long id, String name, String status, CraneType crane_type, List<Request> request, Integer workload) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.crane_type = crane_type;
        this.request = request;
        this.workload = workload;
    }

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
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

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }

    public void incrementWorkload(){
        this.workload++;
    }
    public void dencrementWorkload(){
        this.workload--;
    }
}

