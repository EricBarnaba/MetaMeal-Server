package com.zipcodewilmington.ericb.metamealserver.domain.zomato;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonClassDescription(value = "restaurant")
public class ZomatoRestaurant {

    @Id
    private Long id;

    @OneToOne
    private R r;

    private String name;

    @OneToOne
    private ZomatoLocation location;

    @JsonProperty(value = "average_cost_for_two")
    private Integer price;

    @JsonProperty(value = "user_rating")
    @OneToOne
    private ZomatoRating rating;

    private String cuisines;

    @JsonProperty(value = "phone_numbers")
    private String phoneNumber;

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

    public ZomatoLocation getLocation() {
        return location;
    }

    public void setLocation(ZomatoLocation location) {
        this.location = location;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ZomatoRating getRating() {
        return rating;
    }

    public void setRating(ZomatoRating rating) {
        this.rating = rating;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }
}
