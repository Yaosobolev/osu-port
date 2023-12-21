package com.osu.backend.model.crane;

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

//    @ManyToOne
//    @JoinColumn(name = "request")
//    public Request request;

//  2222
//@OneToMany(mappedBy = "crane")
//private List<Ship> ships;

    @OneToMany(mappedBy = "crane", cascade = CascadeType.ALL)
    private List<Request> request;

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

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }
}

