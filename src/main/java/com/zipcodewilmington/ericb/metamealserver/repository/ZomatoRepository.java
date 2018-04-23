package com.zipcodewilmington.ericb.metamealserver.repository;

import com.zipcodewilmington.ericb.metamealserver.domain.zomato.ZomatoRestaurant;
import org.springframework.data.repository.CrudRepository;

public interface ZomatoRepository extends CrudRepository<ZomatoRestaurant, Long> {
}
