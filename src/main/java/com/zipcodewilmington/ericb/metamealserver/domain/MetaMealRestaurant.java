package com.zipcodewilmington.ericb.metamealserver.domain;

import com.zipcodewilmington.ericb.metamealserver.domain.zomato.ZomatoRestaurant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MetaMealRestaurant {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private Integer rating;
    private String price;

    public MetaMealRestaurant (YelpRestaurant yelp, ZomatoRestaurant zomato){
        this.name = yelp.getName();
        this.address = yelp.getAddress();
        this.phoneNumber = yelp.getPhoneNumber();
        this.rating = calculateMetaRating(yelp,zomato);
        this.price = zomato.getPrice();
    }

    public MetaMealRestaurant(){};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Integer calculateMetaRating(YelpRestaurant yelp, ZomatoRestaurant zomato){
        int totalReview = yelp.getReviewCount() + zomato.getNumberOfRatings();
        double yelpRaw = yelp.getReviewCount() * (yelp.getRating()*20);
        double zomatoRaw = (zomato.getAverageRating()*20) * zomato.getNumberOfRatings();
        double totalRaw = yelpRaw + zomatoRaw;

        return (int) (totalRaw/totalReview);
    }

}
