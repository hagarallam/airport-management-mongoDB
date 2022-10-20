package com.example.spring_mongodb;

import com.example.spring_mongodb.db.AircraftDbReadConvertre;
import com.example.spring_mongodb.db.AircraftDbWriteConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringMongoDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMongoDbApplication.class, args);
    }


    @Bean
    public MongoCustomConversions customConversions(){
        List<Converter<?,?>> converters = new ArrayList<>();
        converters.add(new AircraftDbReadConvertre());
        converters.add(new AircraftDbWriteConverter());
        return new MongoCustomConversions(converters);
    }
}
