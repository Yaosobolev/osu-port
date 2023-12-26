package com.osu.backend.model.schedule;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.osu.backend.model.port.Port;
import com.osu.backend.model.request.Request;
import com.osu.backend.model.ship.Ship;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name = "schedule")
//public class Schedule {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//
//    @Column(name = "arrival_time")
//    private String arrival_time;
//
//    @Column(name = "day_of_stay")
//    private String day_of_stay;
//
//    @ManyToOne
//    @JoinColumn(name = "ship")
//    public Ship ship;
//
//
////    @ManyToOne
////    @JoinColumn(name = "request")
////    public Request request;
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getArrival_time() {
//        return arrival_time;
//    }
//
//    public void setArrival_time(String arrival_time) {
//        this.arrival_time = arrival_time;
//    }
//
//    public String getDay_of_stay() {
//        return day_of_stay;
//    }
//
//    public void setDay_of_stay(String day_of_stay) {
//        this.day_of_stay = day_of_stay;
//    }
//
//    public Ship getShip() {
//        return ship;
//    }
//
//    public void setShip(Ship ship) {
//        this.ship = ship;
//    }
//
////    public Request getRequest() {
////        return request;
////    }
////
////    public void setRequest(Request request) {
////        this.request = request;
////    }
//}

@Entity
@Table
public class Schedule {
    @Id
    @GeneratedValue
//    @Column(name = "id")
    private Long id;


    @OneToMany(mappedBy = "schedule", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("schedule")
//    @JoinColumn(name = "request")
    private List<Request> request  ;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "port")
    @JsonBackReference
    private Port port;


    public Schedule() {

    }

    public Schedule(Long id, List<Request> request, Port port) {
        this.id = id;
        this.request = request;
        this.port = port;
    }

    public Schedule(List<Request> request) {
        this.request = request;
    }

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Request> getRequests() {
        return request;
    }

    public void setRequests(List<Request> requests) {
        this.request = requests;
    }

    public void addRequests(Request request) {
        this.request.add(request);
    }
}