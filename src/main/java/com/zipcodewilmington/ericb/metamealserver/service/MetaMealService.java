package com.zipcodewilmington.ericb.metamealserver.service;

import com.zipcodewilmington.ericb.metamealserver.domain.MetaMealRestaurant;
import com.zipcodewilmington.ericb.metamealserver.domain.Search;
import com.zipcodewilmington.ericb.metamealserver.domain.YelpRestaurant;
import com.zipcodewilmington.ericb.metamealserver.domain.zomato.ZomatoRestaurant;
import com.zipcodewilmington.ericb.metamealserver.repository.MetaMealRepository;
import com.zipcodewilmington.ericb.metamealserver.repository.SearchRepository;
import com.zipcodewilmington.ericb.metamealserver.repository.YelpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class MetaMealService {

    private MetaMealRepository repo;
    private SearchRepository sRepo;
    private YelpRepository yRepo;
    private ZomatoService zomatoService;
    private YelpService yelpService;

    @Autowired
    public MetaMealService(MetaMealRepository repo, ZomatoService zomatoService, YelpService yelpService, YelpRepository yRepo, SearchRepository sRepo){
        this.repo = repo;
        this.zomatoService = zomatoService;
        this.yelpService = yelpService;
        this.sRepo = sRepo;
        this.yRepo = yRepo;
    }

    public List<MetaMealRestaurant> findRestaurants(String city, String state, String cuisine) throws IOException{
        if (isSavedSearch(city,state,cuisine)){
            return sRepo.findByCityAndStateAndCuisine(city,state,cuisine).getResults();
        }
        List<YelpRestaurant> yelpList = yelpService.getRestaurants(city,state,cuisine);
        List<ZomatoRestaurant> zomatoList = zomatoService.getRestaurants(city,state,cuisine);
        List<MetaMealRestaurant> metaList =  mergeLists(yelpList,zomatoList);
        Search search = sRepo.findByCityAndStateAndCuisine(city,state,cuisine);
        Collections.sort(metaList, Comparator.comparing(MetaMealRestaurant::getRating).reversed());
        search.setResults(metaList);
        sRepo.save(search);
        return metaList;
    }

    private boolean isSavedSearch(String city, String state, String cuisine){
        if(sRepo.existsByCityAndAndStateAndCuisine(city,state,cuisine)) return true;
        else {
            sRepo.save(new Search(city,state,cuisine));
            return false;
        }
    }

    private List<MetaMealRestaurant> mergeLists (List<YelpRestaurant> yelpList, List<ZomatoRestaurant> zomatoList){
        List<MetaMealRestaurant> list = new ArrayList<>();
        for (ZomatoRestaurant z : zomatoList){
            if(yRepo.existsByNameAndAddress(z.getName(),z.getAddress())){
                MetaMealRestaurant r = new MetaMealRestaurant(yRepo.findByNameAndAddress(z.getName(),z.getAddress()),z);
                repo.save(r);
                list.add(r);
            }
        }
        return list;
    }



}
