package com.zachholt.MovieDatabaseAPI.services;

import com.zachholt.MovieDatabaseAPI.models.Rating;
import com.zachholt.MovieDatabaseAPI.repos.RatingRepository;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @PostConstruct
    public void initializeRatings() {
        if (ratingRepository.count() == 0) {
            List<Rating> standardRatings = Arrays.asList(
                createRating(1, "G", "General Audience"),
                createRating(2, "PG", "Parental Guidance Suggested"),
                createRating(3, "PG-13", "Parents Strongly Cautioned"),
                createRating(4, "R", "Restricted"),
                createRating(5, "NC-17", "Adults Only")
            );
            ratingRepository.saveAll(standardRatings);
        }
    }

    private Rating createRating(Integer id, String rating, String description) {
        Rating r = new Rating();
        r.setId(id)
         .setRating(rating)
         .setDescription(description);
        return r;
    }

    public List<Rating> findAllRatings() {
        return ratingRepository.findAll();
    }

    public Rating findRatingById(Integer id) {
        return ratingRepository.findById(id).orElse(null);
    }
} 