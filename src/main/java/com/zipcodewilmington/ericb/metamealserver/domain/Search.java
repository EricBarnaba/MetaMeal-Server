package com.zipcodewilmington.ericb.metamealserver.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Search {
    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String state;
    private String cuisine;
    @OneToMany
    private List<MetaMealRestaurant> results;

    public Search(String city, String state, String cuisine) {
        this.city = city;
        this.state = state;
        this.cuisine = cuisine;
        this.results = new ArrayList<>();
    }

    public Search(){}

    public List<MetaMealRestaurant> getResults() {
        return results;
    }

    public void setResults(List<MetaMealRestaurant> results) {
        this.results = results;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
}
