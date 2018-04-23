package com.zipcodewilmington.ericb.metamealserver.repository;

import com.zipcodewilmington.ericb.metamealserver.domain.ZomatoRestaurant;
import org.springframework.data.repository.CrudRepository;

public interface ZomatoRepository extends CrudRepository<ZomatoRestaurant, Long> {
}
