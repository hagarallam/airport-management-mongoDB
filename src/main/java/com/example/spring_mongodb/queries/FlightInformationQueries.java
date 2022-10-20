package com.example.spring_mongodb.queries;

import com.example.spring_mongodb.domain.FlightInformation;
import com.example.spring_mongodb.domain.FlightType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightInformationQueries {

    private MongoTemplate mongoTemplate;

    public FlightInformationQueries(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<FlightInformation> findAll(String field,int pageNb,int pageSize){
        Query allPagedAndOrderd = new Query()
                .with(Sort.by(Sort.Direction.ASC,field))
                .with(PageRequest.of(pageNb,pageSize));

        return this.mongoTemplate.find(allPagedAndOrderd,
                FlightInformation.class);
    }

    public FlightInformation findSingleById(String id){
        return this.mongoTemplate.findById(id,FlightInformation.class);
    }
    public long countInternational(){
        Query international = Query.query(Criteria.where("type")
                .is(FlightType.International));
        return this.mongoTemplate.count(international,FlightInformation.class);
    }

    public List<FlightInformation> findByDeparture(String departure){
        Query byDeparture = new Query()
                .addCriteria(Criteria.where("departure").is(departure));


        return this.mongoTemplate.find(byDeparture, FlightInformation.class);
    }
    public List<FlightInformation> findByDepartureDurationBetween(int min,int max){

                Query byDuration =Query.query(Criteria.where("durationMin")
                        .gte(min).lte(max))
                        .with(Sort.by(Sort.Direction.DESC,"durationMin"));


        return this.mongoTemplate.find(byDuration, FlightInformation.class);
    }

    public List<FlightInformation> findByDelyaedAtDeparture(String departrue){

        Query delayAtDeparture= Query.query(
                Criteria.where("isDelayed").is(true)
                        .and("departrue").is(departrue));

        return this.mongoTemplate.find(delayAtDeparture, FlightInformation.class);
    }



    public List<FlightInformation> findByCityAndNotDelayed(String city){

        Query byCity= Query.query(
                new Criteria().orOperator(
                        Criteria.where("departrue").is(city),
                        Criteria.where("destination").is(city))
                        .andOperator(
                                Criteria.where("isDelayed").is(false))

        );

        return this.mongoTemplate.find(byCity, FlightInformation.class);
    }

    public List<FlightInformation> findByAircraft(String aircraft){

        Query byAircraft = Query.query(
                Criteria.where("aircraft.model").is(aircraft));

        return this.mongoTemplate.find(byAircraft, FlightInformation.class);
    }

    public List<FlightInformation> findByFreeText(String text){

        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matching(text);

        Query byAFullText = TextQuery.queryText(textCriteria).sortByScore().with(PageRequest.of(0,3));
        return this.mongoTemplate.find(byAFullText, FlightInformation.class);
    }


}
