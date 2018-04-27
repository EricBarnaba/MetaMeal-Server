package com.zipcodewilmington.ericb.metamealserver.service.Zomato;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipcodewilmington.ericb.metamealserver.config.ApiKeys;
import com.zipcodewilmington.ericb.metamealserver.domain.zomato.CuisineMap;
import com.zipcodewilmington.ericb.metamealserver.domain.zomato.ZomatoRestaurant;
import com.zipcodewilmington.ericb.metamealserver.repository.ZomatoRepository;
import com.zipcodewilmington.ericb.metamealserver.service.ApiThreader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Service
public class ZomatoService {

    private ZomatoRepository repo;
    private CuisineMap map;
    private ObjectMapper mapper;
    private HttpHeaders headers;


    @Autowired
    public ZomatoService(ZomatoRepository repo, ApiKeys keys, CuisineMap map, ObjectMapper mapper) {
        this.repo = repo;
        this.map = map;
        this.mapper = mapper;
        this.headers = new HttpHeaders();
        headers.set("user-key", keys.getZomatoKey());
    }

    public List<ZomatoRestaurant> getRestaurants(String city, String state, String cuisine) throws IOException {
        List<ZomatoRestaurant> list = new ArrayList<>();
        Integer locationId = getLocationId(city, state);
        Integer cuisineId = getCuisineId(cuisine);
        String firstUrl = "https://developers.zomato.com/api/v2.1/search?entity_id=" + locationId +
                "&entity_type=city&start=" + 0 + "&count=20&cuisines=" + cuisineId;
        String firstCall = makeApiCall(firstUrl).getBody();
        buildRestaurantList(firstCall, list, state);
        int pages = calculateNumberOfPages(firstCall);
        if (pages >= 1) {
            List<String> results = makeThreadedCalls(pages, locationId, cuisineId);
            for (String r : results) {
                buildRestaurantList(r, list, state);
            }
            return list;
        } else return list;
    }

    private List<String> makeThreadedCalls(Integer pages, Integer locationId, Integer cuisineId) {
        List<String> results = new ArrayList<>();
        for (int i = 20; i <= pages * 20; i += 20) {
            String url = "https://developers.zomato.com/api/v2.1/search?entity_id=" + locationId +
                    "&entity_type=city&start=" + i + "&count=20&cuisines=" + cuisineId;
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


    private Integer getCuisineId(String cuisine) {
        return map.getMap().get(cuisine);
    }

    private Integer getLocationId(String city, String state) throws IOException {
        String url = "https://developers.zomato.com/api/v2.1/cities?q=" + city + "%20" + state + "&count=1";
        String rawJson = makeApiCall(url).getBody();
        return mapper.readTree(rawJson).path("location_suggestions").path(0).path("id").asInt();

    }

    private ZomatoRestaurant buildRestaurant(JsonNode node, String state) {
        ZomatoRestaurant r = new ZomatoRestaurant();
        JsonNode n = node.path("restaurant");
        r.setId(n.path("id").asLong());
        r.setName(n.path("name").asText());
        r.setAddress(n.path("location").path("address").asText());
        r.setAverageRating(n.path("user_rating").path("aggregate_rating").asDouble());
        r.setCuisines(n.path("cuisines").asText());
        r.setNumberOfRatings(n.path("user_rating").path("votes").asInt());
        r.setPrice(n.path("average_cost_for_two").asText());
        r.setLat(n.path("location").path("latitude").asDouble());
        r.setLon(n.path("location").path("longitude").asDouble());
        r.setCity(n.path("location").path("city").asText());
        r.setState(state);
        return r;
    }

    private int calculateNumberOfPages(String rawJson) throws IOException {
        int results = mapper.readTree(rawJson).path("results_found").asInt();
        return results / 20 > 4 ? 4 : results / 20;
    }

    private void buildRestaurantList(String rawJson, List<ZomatoRestaurant> list, String state) throws IOException {
        JsonNode fullResponse = mapper.readTree(rawJson);
        JsonNode restaurants = fullResponse.path("restaurants");
        for (JsonNode node : restaurants) {
            ZomatoRestaurant r = buildRestaurant(node, state);
            list.add(r);
            repo.save(r);
        }
    }


}
