package com.zipcodewilmington.ericb.metamealserver.controller;

import com.zipcodewilmington.ericb.metamealserver.config.ApiKeys;
import com.zipcodewilmington.ericb.metamealserver.service.MetaMealService;
import com.zipcodewilmington.ericb.metamealserver.service.ZomatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetaMealController {


    private MetaMealService service;

//    @Autowired
//    private ZomatoService test;


    @Autowired
    public MetaMealController(MetaMealService service){
        this.service = service;
    }

   @RequestMapping(value = "/find/{city}/{state}/{cuisine}", method = RequestMethod.GET)
   public ResponseEntity<?> getRestaurants(@PathVariable("city") String city, @PathVariable("state") String state,
                                            @PathVariable("cuisine") String cuisine){
       return new ResponseEntity<>(service.findRestaurants(city,state,cuisine), HttpStatus.OK);
   }

//    @RequestMapping(value = "/test/{city}/{state}", method = RequestMethod.GET)
//    public ResponseEntity<?> test(@PathVariable("city") String city, @PathVariable("state") String state){
//        return new ResponseEntity<>(test.getLocationId(city,state), HttpStatus.OK);
//    }





}
