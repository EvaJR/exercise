package com.XYZAirline.exercise.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Airplane implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int tonsOfFuel;


    @ManyToOne(cascade = CascadeType.ALL)
    private Airport airport;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTonsOfFuel() {
        return tonsOfFuel;
    }

    public void setTonsOfFuel(int tonsOfFuel) {
        this.tonsOfFuel = tonsOfFuel;
    }
}
