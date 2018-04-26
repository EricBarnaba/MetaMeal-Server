package com.zipcodewilmington.ericb.metamealserver.service;

import com.zipcodewilmington.ericb.metamealserver.domain.MetaMealRestaurant;
import com.zipcodewilmington.ericb.metamealserver.domain.Search;
import com.zipcodewilmington.ericb.metamealserver.domain.YelpRestaurant;
import com.zipcodewilmington.ericb.metamealserver.domain.zomato.ZomatoRestaurant;
import com.zipcodewilmington.ericb.metamealserver.repository.MetaMealRepository;
import com.zipcodewilmington.ericb.metamealserver.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MetaMealService {

    private MetaMealRepository repo;
    private SearchRepository sRepo;
    private ZomatoService zomatoService;
    private YelpService yelpService;

    @Autowired
    public MetaMealService(MetaMealRepository repo, ZomatoService zomatoService, YelpService yelpService){
        this.repo = repo;
        this.zomatoService = zomatoService;
        this.yelpService = yelpService;
    }

    public List<MetaMealRestaurant> findRestaurants(String city, String state, String cuisine) throws IOException{
        if (isSavedSearch(city,state,cuisine)){
            return sRepo.findByCityAndStateAndCuisine(city,state,cuisine).getResults();
        }
        List<YelpRestaurant> yelpList = yelpService.getRestaurants(city,state,cuisine);
        List<ZomatoRestaurant> zomatoList = zomatoService.getRestaurants(city,state,cuisine);
        return null;
    }

    private boolean isSavedSearch(String city, String state, String cuisine){
        if(sRepo.existsByCityAndAndStateAndCuisine(city,state,cuisine)) return true;
        else {
            sRepo.save(new Search(city,state,cuisine));
            return false;
        }
    }



}
