package com.zipcodewilmington.ericb.metamealserver.service;

import com.zipcodewilmington.ericb.metamealserver.domain.MetaMealRestaurant;
import com.zipcodewilmington.ericb.metamealserver.repository.MetaMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaMealService {

    private MetaMealRepository repo;

    @Autowired
    public MetaMealService(MetaMealRepository repo){
        this.repo = repo;
    }

    public List<MetaMealRestaurant> findRestaurants(String city, String state, String cuisine){
        return null;
    }

}
