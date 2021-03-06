package com.zipcodewilmington.ericb.metamealserver.service.Yelp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipcodewilmington.ericb.metamealserver.config.ApiKeys;
import com.zipcodewilmington.ericb.metamealserver.domain.YelpRestaurant;
import com.zipcodewilmington.ericb.metamealserver.repository.YelpRepository;
import com.zipcodewilmington.ericb.metamealserver.service.ApiThreader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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
        List<YelpRestaurant> list = new ArrayList<>();
        String firstUrl =  "https://api.yelp.com/v3/businesses/search?location=" + city + "," + state + "&term=" + cuisine +
                "&limit=50&offset=" + 0;
        String firstCall = makeApiCall(firstUrl).getBody();
        buildRestaurantList(firstCall, list);
        int pages = calculateNumberOfPages(firstCall);
        if (pages >= 1) {
            List<String> results = makeThreadedCalls(pages, city, state,cuisine);
            for (String r : results) {
                buildRestaurantList(r, list);
            }
            return list;
        } else return list;
    }

    private List<String> makeThreadedCalls(Integer pages, String city, String state, String cuisine) {
        List<String> results = new ArrayList<>();
        for (int i = 50; i <= pages * 50; i += 50) {
            String url = "https://api.yelp.com/v3/businesses/search?location=" + city + "," + state + "&term=" + cuisine +
                    "&limit=50&offset=" + i;
            FutureTask<String> call = new FutureTask<>(new ApiThreader(headers, url));
            Thread t = new Thread(call);
            t.start();
            try {
                synchronized (this) {
                    results.add(call.get());
                }
            } catch (InterruptedException | ExecutionException ex) {
                System.out.println(ex.getMessage());
                return null;
            }

        }
        return results;
    }

    private ResponseEntity<String> makeApiCall(String url) {
        HttpEntity entity = new HttpEntity(headers);
        RestTemplate template = new RestTemplate();
        return template.exchange(url, HttpMethod.GET, entity, String.class);
    }

    private int calculateNumberOfPages(String rawJson) throws IOException{
        int results = mapper.readTree(rawJson).path("total").asInt();
        return results / 50 > 20 ? 20 : results / 50;
    }

    private void buildRestaurantList(String rawJson, List<YelpRestaurant> list) throws IOException {
        JsonNode response = mapper.readTree(rawJson);
        JsonNode restaurants = response.path("businesses");
        for(JsonNode node : restaurants){
            YelpRestaurant r = buildRestaurant(node);
            list.add(r);
            repo.save(r);
        }
    }

    private YelpRestaurant buildRestaurant (JsonNode node){
        YelpRestaurant r = new YelpRestaurant();
        r.setId(node.path("id").asText());
        r.setName(node.path("name").asText());
        r.setReviewCount(node.path("review_count").asInt());
        r.setRating(node.path("rating").asDouble());
        r.setPhoneNumber(node.path("display_phone").asText());
        r.setAddress(node.path("location").path("address1").asText() + ", " + node.path("location").path("city").asText() + " " +
                node.path("location").path("zip_code").asText());

        return r;
    }


}
