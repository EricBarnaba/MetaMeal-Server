package com.zipcodewilmington.ericb.metamealserver.service;

import com.zipcodewilmington.ericb.metamealserver.domain.MetaMealRestaurant;
import com.zipcodewilmington.ericb.metamealserver.domain.zomato.ZomatoRestaurant;
import com.zipcodewilmington.ericb.metamealserver.repository.MetaMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MetaMealService {

    private MetaMealRepository repo;
    private ZomatoService zomatoService;
    private YelpService yelpService;

    @Autowired
    public MetaMealService(MetaMealRepository repo, ZomatoService zomatoService, YelpService yelpService){
        this.repo = repo;
        this.zomatoService = zomatoService;
        this.yelpService = yelpService;
    }

    public List<ZomatoRestaurant> findRestaurants(String city, String state, String cuisine) throws IOException{
        return yelpService.getRestaurants(city,state,cuisine);
        //return zomatoService.getRestaurants(city,state,cuisine);
    }



}
