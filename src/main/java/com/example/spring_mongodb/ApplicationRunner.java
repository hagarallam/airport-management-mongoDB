package com.example.spring_mongodb;

import com.example.spring_mongodb.db.FlightInformationRepository;
import com.example.spring_mongodb.domain.FlightInformation;
import com.example.spring_mongodb.domain.FlightPrinter;
import com.example.spring_mongodb.queries.FlightInformationQueries;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@Order(2)
public class ApplicationRunner implements CommandLineRunner {

    private FlightInformationRepository flightInformationRepository;

    public ApplicationRunner(FlightInformationRepository flightInformationRepository) {
        this.flightInformationRepository = flightInformationRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        findById("6351711473465e406d8c62d9");
        delayFlight("6351711473465e406d8c62d9",30);
        removeFlight("6351711473465e406d8c62d9");
        printByDepartureAndDestiation("Rome","New York");
        printByMinNbSeats(300);
    }



    private void findById(String id) {

        FlightInformation flightInformation =
                this.flightInformationRepository.findById(id).get();
        System.out.printf(flightInformation.getDestinationCity());
    }


    private void delayFlight(String id, int time) {
        FlightInformation flightInformation =
                this.flightInformationRepository.findById(id).get();

        flightInformation.setDurationMin(time);

        this.flightInformationRepository.save(flightInformation);
    }

    private void removeFlight(String s) {
        this.flightInformationRepository.deleteById(s);
        System.out.println("Deleted succefully");
    }

    private void printByDepartureAndDestiation
            (String dep, String des) {

        List<FlightInformation> flights = this.flightInformationRepository
                .findByDepartureCityAndDestinationCity(dep,des);
    }
    private void printByMinNbSeats(int i) {

        List<FlightInformation> flights = this.flightInformationRepository
                .findByMinNbSeats(i);
        System.out.println(flights.get(0).getDurationMin());
    }

}




