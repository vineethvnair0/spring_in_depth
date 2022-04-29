package com.vineeth.learning.spring.microservice.repository;

import com.vineeth.learning.spring.microservice.model.TourRating;
import com.vineeth.learning.spring.microservice.model.TourRatingPk;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TourRatingRepository extends CrudRepository<TourRating, TourRatingPk> {
    List<TourRating> findByPkTourId(Integer tourId);

    Optional<TourRating> findByPkTourIdAndPkCustomerId(Integer tourId, Integer customerId);
}
