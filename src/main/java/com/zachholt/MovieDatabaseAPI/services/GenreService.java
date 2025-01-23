package com.zachholt.MovieDatabaseAPI.services;

import com.zachholt.MovieDatabaseAPI.models.Genre;
import com.zachholt.MovieDatabaseAPI.repos.GenreRepository;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    public Genre findGenreById(Integer id) {
        return genreRepository.findById(id).orElse(null);
    }
} 