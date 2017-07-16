package com.example.mongodb.repository;

import com.example.mongodb.entity.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barbarossusuz on 10/07/2017.
 */

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String>, QueryDslPredicateExecutor<Hotel> {

    // http://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongodb.repositories.queries

    Hotel findById(String id);

    // https://docs.mongodb.com/manual/tutorial/query-documents/
    List<Hotel> findByPricePerNightLessThan(int maxPrice);

    // this queries can be done easly with library called qerydsl
    @Query(value = "{address.city:?0}") //standart for mongo query
    List<Hotel> findByCity(String city);
}
