package com.zipcodewilmington.ericb.metamealserver.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class YelpRestaurant {
    @Id
    @GeneratedValue
    private Long id;
}
