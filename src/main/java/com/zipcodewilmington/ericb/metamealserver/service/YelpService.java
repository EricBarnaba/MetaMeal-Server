package com.zipcodewilmington.ericb.metamealserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipcodewilmington.ericb.metamealserver.config.ApiKeys;
import com.zipcodewilmington.ericb.metamealserver.domain.YelpRestaurant;
import com.zipcodewilmington.ericb.metamealserver.repository.YelpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class YelpService {

    private YelpRepository repo;
    private ObjectMapper mapper;
    private HttpHeaders headers;

    @Autowired
    public YelpService(YelpRepository repo, ApiKeys keys, ObjectMapper mapper){
        this.repo= repo;
        this.mapper = mapper;
        this.headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + keys.getYelpKey());
    }

    public List<YelpRestaurant> getRestaurants(String city, String state, String cuisine) throws IOException {
        return null;
    }

    private ResponseEntity<String> makeApiCall(String url) {
        HttpEntity entity = new HttpEntity(headers);
        RestTemplate template = new RestTemplate();
        return template.exchange(url, HttpMethod.GET, entity, String.class);
    }


}
