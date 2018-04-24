package com.zipcodewilmington.ericb.metamealserver.domain.zomato;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ZomatoResponse {

    private List<ZomatoRestaurant> restaurants;
    private int results_found;
    private int results_start;
    private int results_shown;

    public List<ZomatoRestaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<ZomatoRestaurant> restaurant) {
        this.restaurants = restaurant;
    }

    public int getResults_found() {
        return results_found;
    }

    public void setResults_found(int results_found) {
        this.results_found = results_found;
    }

    public int getResults_start() {
        return results_start;
    }

    public void setResults_start(int results_start) {
        this.results_start = results_start;
    }

    public int getResults_shown() {
        return results_shown;
    }

    public void setResults_shown(int results_shown) {
        this.results_shown = results_shown;
    }
}
