package com.example.spring_mongodb.domain;

public class Aircraft {
    
    private String model;
    private int nbSeats;

    public Aircraft(String model, int nbSeats) {
        this.model = model;
        this.nbSeats = nbSeats;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setNbSeats(int nbSeats) {
        this.nbSeats = nbSeats;
    }

    public int getNbSeats() {
        return nbSeats;
    }

}
