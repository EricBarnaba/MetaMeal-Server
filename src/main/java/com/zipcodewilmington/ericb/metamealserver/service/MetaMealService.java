package com.zipcodewilmington.ericb.metamealserver.service;

import com.zipcodewilmington.ericb.metamealserver.exception.CuisineNotFoundException;
import com.zipcodewilmington.ericb.metamealserver.domain.MetaMealRestaurant;
import com.zipcodewilmington.ericb.metamealserver.domain.Search;
import com.zipcodewilmington.ericb.metamealserver.domain.YelpRestaurant;
import com.zipcodewilmington.ericb.metamealserver.domain.zomato.ZomatoRestaurant;
import com.zipcodewilmington.ericb.metamealserver.repository.MetaMealRepository;
import com.zipcodewilmington.ericb.metamealserver.repository.SearchRepository;
import com.zipcodewilmington.ericb.metamealserver.repository.YelpRepository;
import com.zipcodewilmington.ericb.metamealserver.service.Yelp.YelpService;
import com.zipcodewilmington.ericb.metamealserver.service.Yelp.YelpThreader;
import com.zipcodewilmington.ericb.metamealserver.service.Zomato.ZomatoService;
import com.zipcodewilmington.ericb.metamealserver.service.Zomato.ZomatoThreader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Service
public class MetaMealService {

    private MetaMealRepository repo;
    private SearchRepository sRepo;
    private YelpRepository yRepo;
    private ZomatoService zomatoService;
    private YelpService yelpService;

    @Autowired
    public MetaMealService(MetaMealRepository repo, ZomatoService zomatoService, YelpService yelpService, YelpRepository yRepo, SearchRepository sRepo) {
        this.repo = repo;
        this.zomatoService = zomatoService;
        this.yelpService = yelpService;
        this.sRepo = sRepo;
        this.yRepo = yRepo;
    }

    public List<MetaMealRestaurant> findRestaurants(String city, String state, String cuisine) throws IOException, CuisineNotFoundException {
        if (isSavedSearch(city, state, cuisine)) {
            return sRepo.findByCityAndStateAndCuisine(city, state, cuisine).getResults();
        }
        FutureTask<List<ZomatoRestaurant>> zomato = new FutureTask<>(new ZomatoThreader(city, state, cuisine, zomatoService));
        FutureTask<List<YelpRestaurant>> yelp = new FutureTask<>(new YelpThreader(city, state, cuisine, yelpService));
        Thread z = new Thread(zomato);
        Thread y = new Thread(yelp);
        z.start();
        y.start();
        try {
            List<ZomatoRestaurant> zomatoList = zomato.get();
            List<YelpRestaurant> yelpList = yelp.get();
            List<MetaMealRestaurant> metaList = mergeLists(yelpList, zomatoList);
            Search search = sRepo.findByCityAndStateAndCuisine(city, state, cuisine);
            Collections.sort(metaList, Comparator.comparing(MetaMealRestaurant::getRating).reversed());
            search.setResults(metaList);
            sRepo.save(search);
            return metaList;
        } catch (InterruptedException | ExecutionException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private boolean isSavedSearch(String city, String state, String cuisine) {
        if (sRepo.existsByCityAndAndStateAndCuisine(city, state, cuisine)) return true;
        else {
            sRepo.save(new Search(city, state, cuisine));
            return false;
        }
    }

    private List<MetaMealRestaurant> mergeLists(List<YelpRestaurant> yelpList, List<ZomatoRestaurant> zomatoList) {
        List<MetaMealRestaurant> list = new ArrayList<>();
        for (ZomatoRestaurant z : zomatoList) {
            if (yRepo.existsByNameAndAddress(z.getName(), z.getAddress())) {
                MetaMealRestaurant r = new MetaMealRestaurant(yRepo.findByNameAndAddress(z.getName(), z.getAddress()), z);
                repo.save(r);
                list.add(r);
            }
        }
        return list;
    }


}
