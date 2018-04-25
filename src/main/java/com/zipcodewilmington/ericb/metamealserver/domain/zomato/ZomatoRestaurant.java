package com.zipcodewilmington.ericb.metamealserver.domain.zomato;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



import javax.persistence.*;

@Entity
public class ZomatoRestaurant {

    @Id
    private Long id;
    private String name;
    private String address;
    private Integer price;
    private Double averageRating;
    private Integer numberOfRatings;
    private String cuisines;
    private Double lat;
    private Double lon;
    private String city;
    private String state;

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public void setNumberOfRatings(Integer numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
