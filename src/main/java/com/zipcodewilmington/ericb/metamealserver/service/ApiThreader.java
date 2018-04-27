package com.zipcodewilmington.ericb.metamealserver.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.Callable;

public class ApiThreader implements Callable<String> {

    private HttpHeaders headers;
    private String url;

    public ApiThreader(HttpHeaders headers, String url){
        this.headers = headers;
        this.url = url;
    }


    @Override
    public String call() throws Exception{
        HttpEntity entity = new HttpEntity(headers);
        RestTemplate template = new RestTemplate();
        return template.exchange(url, HttpMethod.GET, entity, String.class).getBody();
    }
}
