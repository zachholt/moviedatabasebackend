package com.zachholt.MovieDatabaseAPI.repos;

import com.zachholt.MovieDatabaseAPI.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
} 