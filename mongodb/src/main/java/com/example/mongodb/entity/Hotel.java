package com.example.mongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by barbarossusuz on 10/07/2017.
 */

@Document( collection = "Hotels")
public class Hotel {

    @Id
    private String id;

    private String name;
    @Indexed(direction = IndexDirection.ASCENDING)
    private int pricePerNight;

    private Address address;
    private List<Review> reviewList;


    protected Hotel() { this.reviewList = new ArrayList<>();}

    public Hotel(String name, int pricePerNight, Address adress, List<Review> reviewList) {
        this.name = name;
        this.pricePerNight = pricePerNight;
        this.address = adress;
        this.reviewList = reviewList;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public Address getAdress() {
        return address;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }
}
