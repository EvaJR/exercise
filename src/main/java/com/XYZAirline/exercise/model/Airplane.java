package com.XYZAirline.exercise.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Airplane implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int tonsOfFuel;

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
