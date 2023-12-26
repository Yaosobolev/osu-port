package com.osu.backend.model.port;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.osu.backend.model.crane.Crane;
import com.osu.backend.model.request.Request;
import com.osu.backend.model.schedule.Schedule;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "port")
public class Port {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "port", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("port")
//    @JoinColumn(name = "request")
    private List<Schedule> schedules  ;

    public Port(){}
    public Port(Long id, List<Schedule> schedules) {
        this.id = id;
        this.schedules = schedules;
    }

    public List<Schedule> getRequest() {
        return schedules;
    }

    public void setRequest(List<Schedule> request) {
        this.schedules = request;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
