package com.example.spring_mongodb.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("airports")
public class Airport {

    @Id
    private String id;
    private String name;
    private String city;
    private int passengerServed;

    public Airport(String id, String name, String city, int passengerServed) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.passengerServed = passengerServed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPassengerServed() {
        return passengerServed;
    }

    public void setPassengerServed(int passengerServed) {
        this.passengerServed = passengerServed;
    }
}
