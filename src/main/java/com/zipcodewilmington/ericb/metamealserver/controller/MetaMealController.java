package com.zipcodewilmington.ericb.metamealserver.controller;

import com.zipcodewilmington.ericb.metamealserver.config.ApiKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetaMealController {

    @Autowired
    private ApiKeys keys;

    @RequestMapping(name = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> testMethod(){
        return new ResponseEntity<>(keys.getYelpKey(), HttpStatus.OK);
    }

}
