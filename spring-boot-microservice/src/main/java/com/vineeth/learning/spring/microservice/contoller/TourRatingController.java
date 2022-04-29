package com.vineeth.learning.spring.microservice.contoller;

import com.vineeth.learning.spring.microservice.model.Tour;
import com.vineeth.learning.spring.microservice.model.TourRating;
import com.vineeth.learning.spring.microservice.model.TourRatingPk;
import com.vineeth.learning.spring.microservice.repository.TourRatingRepository;
import com.vineeth.learning.spring.microservice.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tours/{tourId}/ratings")
public class TourRatingController {

    @Autowired
    private TourRatingRepository tourRatingRepository;

    @Autowired
    private TourRepository tourRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTourRating(@PathVariable(value = "tourId") int tourId, @RequestBody  RatingDto ratingDto){
        Tour tour = verifyTour(tourId);
        tourRatingRepository.save(new TourRating(
                new TourRatingPk(tour, ratingDto.getCustomerId()), ratingDto.getScore(), ratingDto.getComment()));

    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<RatingDto> getAllRatingsForTour(@PathVariable(value = "tourId") int tourId) {
        verifyTour(tourId);
        return tourRatingRepository.findByPkTourId(tourId).stream().map(RatingDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/average")
    public Map<String, Double> getAverage(@PathVariable(value = "tourId") int tourId){
        verifyTour(tourId);
        return Map.of("average", tourRatingRepository.findByPkTourId(tourId
        ).stream().mapToInt(TourRating::getScore
        ).average().orElseThrow(() -> new NoSuchElementException("Tour has No Rating")));
    }

    @PutMapping
    public RatingDto updateWithPut(@PathVariable(value = "tourId") int tourId, @RequestBody RatingDto ratingDto){
        TourRating tourRating = verifyTourRating(tourId, ratingDto.getCustomerId());
        tourRating.setScore(ratingDto.getScore());
        tourRating.setComment(ratingDto.getComment());
        return new RatingDto(tourRatingRepository.save(tourRating));
    }

    @DeleteMapping(path = "/{customerId}")
    public void delete(@PathVariable(value = "tourId") int tourId, @PathVariable(value = "customerId") int customerId){
        TourRating tourRating = verifyTourRating(tourId, customerId);
        tourRatingRepository.delete(tourRating);
    }

    @PatchMapping
    public RatingDto updateWithPatch(@PathVariable(value = "tourId") int tourId, @RequestBody RatingDto ratingDto){
        TourRating tourRating = verifyTourRating(tourId, ratingDto.getCustomerId());
        if (ratingDto.getScore() != null) {
            tourRating.setScore(ratingDto.getScore());
        }
        if (ratingDto.getComment() != null){
            tourRating.setComment(ratingDto.getComment());
        }
        return new RatingDto(tourRatingRepository.save(tourRating));
    }

    private Tour verifyTour(int tourId){
        return tourRepository.findById(tourId).orElseThrow(() -> new NoSuchElementException("Tour Does not exist : "+ tourId
        ));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    private String return400(NoSuchElementException noSuchElementException){
        return noSuchElementException.getMessage();
    }

    private TourRating verifyTourRating(int tourId, int customerd) {
        return tourRatingRepository.findByPkTourIdAndPkCustomerId(tourId,
                customerd).orElseThrow(() -> new NoSuchElementException("Tour rating not found"));
    }
}
