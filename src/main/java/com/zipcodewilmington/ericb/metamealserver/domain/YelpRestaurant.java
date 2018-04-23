package com.zipcodewilmington.ericb.metamealserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class YelpRestaurant {
    @Id
    @GeneratedValue
    private Long id;
}
