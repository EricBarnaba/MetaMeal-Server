package com.zipcodewilmington.ericb.metamealserver.repository;

import com.zipcodewilmington.ericb.metamealserver.domain.Search;
import org.springframework.data.repository.CrudRepository;

public interface SearchRepository extends CrudRepository<Search,Long> {
     boolean existsByCityAndAndStateAndCuisine(String city, String state, String cuisine);
     Search findByCityAndStateAndCuisine(String city, String state, String cuisine);
}
