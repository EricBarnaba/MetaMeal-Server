package com.zipcodewilmington.ericb.metamealserver.service;

import com.zipcodewilmington.ericb.metamealserver.config.ApiKeys;
import com.zipcodewilmington.ericb.metamealserver.domain.zomato.CuisineMap;
import com.zipcodewilmington.ericb.metamealserver.domain.zomato.SuggestedLocation;
import com.zipcodewilmington.ericb.metamealserver.domain.zomato.ZomatoResponse;
import com.zipcodewilmington.ericb.metamealserver.domain.zomato.ZomatoRestaurant;
import com.zipcodewilmington.ericb.metamealserver.repository.ZomatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZomatoService {

    private ZomatoRepository repo;
    private ApiKeys keys;
    private CuisineMap map;

    @Autowired
    public ZomatoService(ZomatoRepository repo, ApiKeys keys, CuisineMap map){
        this.repo = repo;
        this.keys = keys;
        this.map = map;
    }

    public List<ZomatoRestaurant> getRestaurants(String city, String state, String cuisine){
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-key", keys.getZomatoKey());
        HttpEntity entity = new HttpEntity(headers);
        String url = "https://developers.zomato.com/api/v2.1/search?entity_id=" + getLocationId(city,state) +
                "&cuisines=" + getCuisineId(cuisine);
        ResponseEntity<ZomatoResponse> response = template.exchange(url, HttpMethod.GET, entity, ZomatoResponse.class);
        return response.getBody().getRestaurants();

    }


    private Integer getCuisineId(String cuisine){
        return map.getMap().get(cuisine);
    }

    private Integer getLocationId(String city, String state){
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-key", keys.getZomatoKey());
        HttpEntity entity = new HttpEntity(headers);
        String url = "https://developers.zomato.com/api/v2.1/cities?q=" + city + "%20" + state + "&count=1";
        ResponseEntity<?> response = template.exchange(url, HttpMethod.GET, entity, SuggestedLocation.class);

        SuggestedLocation location = (SuggestedLocation) response.getBody();

        return location.getLocation_suggestions().get(0).getId();
    }


}
