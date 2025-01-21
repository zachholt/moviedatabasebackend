package com.zachholt.MovieDatabaseAPI.repos;

import com.zachholt.MovieDatabaseAPI.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
} 