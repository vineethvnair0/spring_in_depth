package com.vineeth.learning.spring.microservice.repository;

import com.vineeth.learning.spring.microservice.model.TourPackage;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TourPackageRepository extends CrudRepository<TourPackage, String> {

    Optional<TourPackage> findByName(String name);
}
