package com.zipcodewilmington.ericb.metamealserver.service.Zomato;

import com.zipcodewilmington.ericb.metamealserver.domain.zomato.ZomatoRestaurant;

import java.util.List;
import java.util.concurrent.Callable;

public class ZomatoThreader implements Callable<List<ZomatoRestaurant>> {
    private String city;
    private String state;
    private String cuisine;
    private ZomatoService service;

    public ZomatoThreader(String city, String state, String cuisine, ZomatoService service){
        this.city = city;
        this.state = state;
        this.cuisine = cuisine;
        this.service = service;
    }
    @Override
    public List<ZomatoRestaurant> call() throws Exception {
        return service.getRestaurants(city,state,cuisine);
    }
}
