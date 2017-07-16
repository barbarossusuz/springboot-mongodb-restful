package com.example.mongodb.controller;


import com.example.mongodb.repository.HotelRepository;
import com.example.mongodb.entity.Hotel;
import com.example.mongodb.entity.QHotel;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by barbarossusuz on 10/07/2017.
 */

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;


    @GetMapping
    public List<Hotel> getAll(){
        List<Hotel> hotels = this.hotelRepository.findAll();
        return hotels;
    }

    @PutMapping
    public void update(@RequestBody Hotel hotel){
        this.hotelRepository.insert(hotel);
    }
    @PostMapping
    public void create(@RequestBody Hotel hotel){
        this.hotelRepository.save(hotel);
    }

    @DeleteMapping("/id")
    public void delete(@PathVariable("id") String id){
        this.hotelRepository.delete(id);
    }

    @GetMapping("/{id}")
    public Hotel getById (@PathVariable("id") String id){
        Hotel hotel = this.hotelRepository.findById(id);

        return hotel;
    }

    @GetMapping("/price/{maxPrice}")
    public List<Hotel> getByPricePerNight (@PathVariable("maxPrice") int maxPrice){
        List<Hotel> hotels = this.hotelRepository.findByPricePerNightLessThan(maxPrice);

        return hotels;
    }

    @GetMapping("/address/{city}")
    public List<Hotel> getByCity(@PathVariable("city") String city){
        List<Hotel> hotels = this.hotelRepository.findByCity(city);

        return hotels;
    }

    @GetMapping("/country/{country}")
    public List<Hotel> getByCountry(@PathVariable("country") String country){
        // create a query class (QHotel)
        QHotel qHotel = new QHotel("hotel");

        // using the query class we can create the filters
        BooleanExpression filterByCountry = qHotel.address.country.eq(country);

        // we can then pass the filters to the findAll() method
        List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByCountry);

        return hotels;
    }

    // find hotels with pricePerNight given value and at least 1 reviews with a rating greater than 7
//    localhost:8080/priceandrating with body json
//    {
//        "maxPrice" : 300,
//        "minRating" : 6
//    }
    @PostMapping("/priceandrating")
    public List<Hotel> getByPriceAndReviews(@RequestBody HashMap<String, Integer> map){

        int maxPrice=map.get("maxPrice");
        int minRating=map.get("minRating");
        QHotel qHotel = new QHotel("hotel");

        // lt= lesser then
        BooleanExpression filterByPrice = qHotel.pricePerNight.lt(maxPrice);

        // gt= greater then
        BooleanExpression filterBReview = qHotel.reviewList.any().rating.gt(minRating);

        List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByPrice.and(filterBReview));

        return hotels;
    }
}
