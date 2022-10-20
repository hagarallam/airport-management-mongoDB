package com.example.spring_mongodb.db;


import com.example.spring_mongodb.domain.FlightInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightInformationRepository extends MongoRepository<FlightInformation,String> {


    List<FlightInformation> findByDepartureCityAndDestinationCity(String dep, String des);

    @Query("{'aircraft.nbSeats' : { $gte : ?0 } }")
    List<FlightInformation> findByMinNbSeats(int noSeats);
}
