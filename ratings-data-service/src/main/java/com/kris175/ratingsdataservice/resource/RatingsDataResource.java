package com.kris175.ratingsdataservice.resource;

import com.kris175.ratingsdataservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {
    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId, 4);
    }
}
