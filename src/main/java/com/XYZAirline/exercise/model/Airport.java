package com.XYZAirline.exercise.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Airport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "airport")
    private Set<Airplane> airplanes;

    public Set<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(Set<Airplane> airplanes) {
        this.airplanes = airplanes;
    }

    // helper method to make sure both sides are updated
    public void addAirplanes(Airplane airplane) {
        this.airplanes.add(airplane);
        airplane.setAirport(this);
    }

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
