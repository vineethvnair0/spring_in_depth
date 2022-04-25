package com.vineeth.learning.spring.microservice.service;

import com.vineeth.learning.spring.microservice.model.TourPackage;
import com.vineeth.learning.spring.microservice.repository.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourPackageService {

    @Autowired
    private TourPackageRepository tourPackageRepository;

    /**
     * create a tour package
     *
     * @param code of the package
     * @param name of the package
     * @return new or existing tour package
     */
    public TourPackage createTourPackage(String code, String name){
        return tourPackageRepository.findById(code)
                .orElse(tourPackageRepository.save(new TourPackage(code, name)));
    }

    /**
     * Lookup all tour packages
     * @return all tour packages
     */
    public Iterable<TourPackage> lookup() {
        return tourPackageRepository.findAll();
    }

    /**
     * Total number of tour packages
     * @return Total number of tour packages
     */
    public long total(){
        return tourPackageRepository.count();
    }
}
