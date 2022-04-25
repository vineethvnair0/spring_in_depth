package com.vineeth.learning.spring.microservice.repository;

import com.vineeth.learning.spring.microservice.model.Tour;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<Tour, Integer> {
}
