package com.zipcodewilmington.ericb.metamealserver.domain.zomato;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



import javax.persistence.*;
import java.util.Objects;

@Entity
public class ZomatoRestaurant {

    @Id
    private Long id;
    private String name;
    private String address;
    private String price;
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

    public Double getAverageRating() {
        return averageRating;
    }

    public Integer getNumberOfRatings() {
        return numberOfRatings;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price.equals("0") ?  "N/A" : "$"+price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZomatoRestaurant that = (ZomatoRestaurant) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getPrice(), that.getPrice()) &&
                Objects.equals(getAverageRating(), that.getAverageRating()) &&
                Objects.equals(getNumberOfRatings(), that.getNumberOfRatings()) &&
                Objects.equals(getCuisines(), that.getCuisines()) &&
                Objects.equals(getLat(), that.getLat()) &&
                Objects.equals(getLon(), that.getLon()) &&
                Objects.equals(getCity(), that.getCity()) &&
                Objects.equals(getState(), that.getState());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getAddress(), getPrice(), getAverageRating(), getNumberOfRatings(), getCuisines(), getLat(), getLon(), getCity(), getState());
    }
}
