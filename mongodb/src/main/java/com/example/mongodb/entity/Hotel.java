package com.example.mongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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


    public Hotel() {
    }

    public Hotel(String name, int pricePerNight, Address address, List<Review> reviewList) {
        this.name = name;
        this.pricePerNight = pricePerNight;
        this.address = address;
        this.reviewList = reviewList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}
