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

    @PostConstruct
    public void initializeGenres() {
        if (genreRepository.count() == 0) {
            List<Genre> standardGenres = Arrays.asList(
                createGenre(1, "Action", "Action movies"),
                createGenre(2, "Adventure", "Adventure movies"),
                createGenre(3, "Comedy", "Comedy movies"),
                createGenre(4, "Drama", "Drama movies"),
                createGenre(5, "Fantasy", "Fantasy movies"),
                createGenre(6, "Horror", "Horror movies"),
                createGenre(7, "Mystery", "Mystery movies"),
                createGenre(8, "Romance", "Romance movies"),
                createGenre(9, "Science Fiction", "Science Fiction movies"),
                createGenre(10, "Thriller", "Thriller movies")
            );
            genreRepository.saveAll(standardGenres);
        }
    }

    private Genre createGenre(Integer id, String name, String description) {
        Genre genre = new Genre();
        genre.setId(id)
            .setGenre(name)
            .setDescription(description);
        return genre;
    }

    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    public Genre findGenreById(Integer id) {
        return genreRepository.findById(id).orElse(null);
    }
} 