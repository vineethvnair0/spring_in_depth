package com.vineeth.learning.spring.microservice.service;

import com.vineeth.learning.spring.microservice.model.Difficulty;
import com.vineeth.learning.spring.microservice.model.Region;
import com.vineeth.learning.spring.microservice.model.Tour;
import com.vineeth.learning.spring.microservice.model.TourPackage;
import com.vineeth.learning.spring.microservice.repository.TourPackageRepository;
import com.vineeth.learning.spring.microservice.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourPackageRepository tourPackageRepository;

    /**
     * Create a new tour
     * @param title
     * @param description
     * @param blurb
     * @param price
     * @param duration
     * @param bullets
     * @param keywords
     * @param tourPackageName
     * @param difficulty
     * @param region
     * @return Tour Entity
     */
    public Tour createTour(String title, String description, String blurb, Integer price,
                           String duration, String bullets, String keywords, String tourPackageName,
                           Difficulty difficulty, Region region){
        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName)
                .orElseThrow(() -> new RuntimeException("Tour Package dosen't exist"));

        Tour tour = new Tour(title, description, blurb, price, duration, bullets, keywords, tourPackage, difficulty, region);
        return tourRepository.save(tour);
    }

    /**
     * Get total Number of Tours
     *
     * @return total Number of Tours
     */
    public long total() {
        return tourRepository.count();
    }
}
