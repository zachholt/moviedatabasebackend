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

    public List<Rating> findAllRatings() {
        return ratingRepository.findAll();
    }

    public Rating findRatingById(Integer id) {
        return ratingRepository.findById(id).orElse(null);
    }
} 