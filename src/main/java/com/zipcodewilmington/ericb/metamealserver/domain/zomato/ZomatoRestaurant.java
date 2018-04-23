package com.zipcodewilmington.ericb.metamealserver.domain.zomato;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zipcodewilmington.ericb.metamealserver.domain.zomato.ZomatoLocation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZomatoRestaurant {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private ZomatoLocation location;

    @JsonProperty(value = "average_cost_for_two")
    private Integer price;

    @JsonProperty(value = "user_rating")
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
}
