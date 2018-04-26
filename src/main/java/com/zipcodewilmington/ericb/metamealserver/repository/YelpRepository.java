package com.zipcodewilmington.ericb.metamealserver.repository;

import com.zipcodewilmington.ericb.metamealserver.domain.YelpRestaurant;
import org.springframework.data.repository.CrudRepository;

public interface YelpRepository extends CrudRepository<YelpRestaurant,Long> {
    YelpRestaurant findByNameAndAddress(String name, String address);
}
