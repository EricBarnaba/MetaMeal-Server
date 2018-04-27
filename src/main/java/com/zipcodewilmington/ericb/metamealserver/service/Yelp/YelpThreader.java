package com.zipcodewilmington.ericb.metamealserver.service.Yelp;

import com.zipcodewilmington.ericb.metamealserver.domain.YelpRestaurant;

import java.util.List;
import java.util.concurrent.Callable;

public class YelpThreader implements Callable<List<YelpRestaurant>> {
    private String city;
    private String state;
    private String cuisine;
    private YelpService service;

    public YelpThreader(String city, String state, String cuisine, YelpService service){
        this.city = city;
        this.state = state;
        this.cuisine = cuisine;
        this.service = service;
    }
    @Override
    public List<YelpRestaurant> call() throws Exception {
        return service.getRestaurants(city,state,cuisine);
    }
}
