package com.example.spring_mongodb.db;

import com.example.spring_mongodb.domain.Aircraft;
import org.springframework.core.convert.converter.Converter;

public class AircraftDbReadConvertre implements Converter<String, Aircraft> {


    @Override
    public Aircraft convert(String s) {
        if(s == null){
            return null;
        }
        String [] parts = s.split("/");
        Aircraft aircraft = new Aircraft(parts[0],Integer.parseInt(parts[1]));
        return aircraft;
    }
}
