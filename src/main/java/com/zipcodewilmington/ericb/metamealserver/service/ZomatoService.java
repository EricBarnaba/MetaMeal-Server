package com.zipcodewilmington.ericb.metamealserver.service;

import com.zipcodewilmington.ericb.metamealserver.domain.zomato.ZomatoRestaurant;
import com.zipcodewilmington.ericb.metamealserver.repository.ZomatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZomatoService {

    private ZomatoRepository repo;

    @Autowired
    public ZomatoService(ZomatoRepository repo){
        this.repo = repo;
    }

    public List<ZomatoRestaurant> getRestaurants(String city, String state, String cuisine){
        return null;
    }

    private Integer getCuisineId(String cuisine){
        return null;
    }

    private Integer getLocationId(String city, String state){
        return null;
    }


}
