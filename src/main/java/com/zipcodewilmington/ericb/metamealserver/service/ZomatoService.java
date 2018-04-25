package com.zipcodewilmington.ericb.metamealserver.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipcodewilmington.ericb.metamealserver.config.ApiKeys;
import com.zipcodewilmington.ericb.metamealserver.domain.zomato.CuisineMap;
import com.zipcodewilmington.ericb.metamealserver.domain.zomato.ZomatoRestaurant;
import com.zipcodewilmington.ericb.metamealserver.repository.ZomatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ZomatoService {

    private ZomatoRepository repo;
    private ApiKeys keys;
    private CuisineMap map;
    private ObjectMapper mapper;

    @Autowired
    public ZomatoService(ZomatoRepository repo, ApiKeys keys, CuisineMap map, ObjectMapper mapper){
        this.repo = repo;
        this.keys = keys;
        this.map = map;
        this.mapper = mapper;
    }

    public List<ZomatoRestaurant> getRestaurants(String city, String state, String cuisine){
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-key", keys.getZomatoKey());
        HttpEntity entity = new HttpEntity(headers);

        String url = "https://developers.zomato.com/api/v2.1/search?entity_id=" + getLocationId(city,state) +
                "&entity_type=city&cuisines=" + getCuisineId(cuisine);

        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class);
        List<ZomatoRestaurant> list = new ArrayList<>();
        try {
           JsonNode fullResponse = mapper.readTree(response.getBody());
           JsonNode restaurants = fullResponse.path("restaurants");
           for(JsonNode node : restaurants){
               ZomatoRestaurant r = new ZomatoRestaurant();
               JsonNode n = node.path("restaurant");
               r.setId(n.path("id").asLong());
               r.setName(n.path("name").asText());
               r.setAddress(n.path("location").path("address").asText());
               r.setAverageRating(n.path("user_rating").path("aggregate_rating").asDouble());
               r.setCuisines(n.path("cuisines").asText());
               r.setNumberOfRatings(n.path("user_rating").path("votes").asInt());
               r.setPrice(n.path("average_cost_for_two").asInt());
               r.setLat(n.path("location").path("latitude").asDouble());
               r.setLon(n.path("location").path("longitude").asDouble());
               r.setCity(n.path("location").path("city").asText());
               r.setState(state);
               list.add(r);
               repo.save(r);
           }
           return list;
        }
        catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
        return null;
    }


    private Integer getCuisineId(String cuisine){
        return map.getMap().get(cuisine);
    }

    public Integer getLocationId(String city, String state){
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-key", keys.getZomatoKey());
        HttpEntity entity = new HttpEntity(headers);
        String url = "https://developers.zomato.com/api/v2.1/cities?q=" + city + "%20" + state + "&count=1";
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class);

        try {
            JsonNode fullResponse = mapper.readTree(response.getBody());
            JsonNode suggestions = fullResponse.path("location_suggestions");
            return suggestions.path(0).path("id").asInt();
        }
        catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
        return null;
    }


}
