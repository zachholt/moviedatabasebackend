package com.zachholt.MovieDatabaseAPI.repos;

import com.zachholt.MovieDatabaseAPI.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
} 