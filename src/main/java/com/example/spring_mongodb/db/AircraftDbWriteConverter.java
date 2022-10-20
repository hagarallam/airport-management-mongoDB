package com.example.spring_mongodb.db;

import com.example.spring_mongodb.domain.Aircraft;
import org.springframework.core.convert.converter.Converter;


public class AircraftDbWriteConverter implements Converter<Aircraft,String> {


    @Override
    public String convert(Aircraft aircraft) {
        return aircraft.getModel()+ "/" + aircraft.getNbSeats();
    }
}
