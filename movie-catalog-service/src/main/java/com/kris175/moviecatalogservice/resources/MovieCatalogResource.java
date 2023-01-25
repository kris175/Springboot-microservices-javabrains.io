package com.kris175.moviecatalogservice.resources;

import com.kris175.moviecatalogservice.models.CatalogItem;
import com.kris175.moviecatalogservice.models.Movie;
import com.kris175.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        // get all rated movieIds - hard coded for now
        List<Rating> ratings = Arrays.asList(
                new Rating("123", 5),
                new Rating("456", 4),
                new Rating("789", 3)
        );

        // For each movieId call movie info service and get details - make api call using REST template
        return ratings.stream().map(rating -> {
                    Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
                }
        ).collect(Collectors.toList());

        // collate both info

    }
}
