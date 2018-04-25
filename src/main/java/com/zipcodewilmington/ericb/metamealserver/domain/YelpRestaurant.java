package com.zipcodewilmington.ericb.metamealserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class YelpRestaurant {
    @Id
    private String id;
    private String name;
    private Integer reviewCount;
    private Double rating;
    private String phoneNumber;
    private String address;
}
