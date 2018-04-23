package com.zipcodewilmington.ericb.metamealserver.repository;

import com.zipcodewilmington.ericb.metamealserver.domain.MetaMealRestaurant;
import org.springframework.data.repository.CrudRepository;

public interface MetaMealRepository extends CrudRepository<MetaMealRestaurant, Long> {
}
