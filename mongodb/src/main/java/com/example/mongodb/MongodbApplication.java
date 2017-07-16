package com.example.mongodb;


import com.example.mongodb.repository.HotelRepository;
import com.example.mongodb.entity.Address;
import com.example.mongodb.entity.Hotel;
import com.example.mongodb.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.List;


/**
 * Created by barbarossusuz on 10/07/2017.
 */
@Component
@SpringBootApplication
public class MongodbApplication implements CommandLineRunner {


    @Autowired
    private HotelRepository hotelRepository;


    @Override
    public void run(String... strings) throws Exception {
        Hotel fourSeasons = new Hotel(
                "FourSeasons",
                200,
                new Address("İstanbul", "Turkey"),
                Arrays.asList(
                        new Review("Ahmet",6, false),
                        new Review("Emel",7, true)
                )
        );

        Hotel ibiza = new Hotel(
                "İbiza",
                250,
                new Address("İstanbul", "Turkey"),
                Arrays.asList(
                        new Review("Mehmet",8, true)
                )
        );

        Hotel mayFair = new Hotel(
                "The May Fair Hotel",
                300,
                new Address("Londra", "England"),
                Arrays.asList(
                        new Review("Mehmet",10, true)
                )
        );

        Hotel pullman = new Hotel(
                "Hotel Pullman Paris Tour Eiffel",
                400,
                new Address("Paris", "France"),
                Arrays.asList(
                        new Review("Mehmet",10, true)
                )
        );

        // delete all hotels
        this.hotelRepository.deleteAll();

        //add hotels
        List<Hotel> hotels = Arrays.asList(fourSeasons,ibiza,mayFair,pullman);
        this.hotelRepository.save(hotels);
    }

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class, args);
    }
}
