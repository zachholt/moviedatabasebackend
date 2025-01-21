package com.zachholt.MovieDatabaseAPI.services;

import com.zachholt.MovieDatabaseAPI.clients.MovieClient;
import com.zachholt.MovieDatabaseAPI.mappers.MovieMapper;
import com.zachholt.MovieDatabaseAPI.models.Movie;
import com.zachholt.MovieDatabaseAPI.models.movieExternal.MovieDatabaseItem;
import com.zachholt.MovieDatabaseAPI.repos.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final MovieClient movieClient;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper, MovieClient movieClient) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.movieClient = movieClient;
    }

    public Movie findMovieById(Integer id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            // Get movie overview from TMDB API
            MovieDatabaseItem movieDatabaseItem = movieClient.searchMovie(movie.getMovieTitle())
                    .block();
            
            if (movieDatabaseItem != null) {
                movie = movieMapper.updateMovieWithOverview(movieDatabaseItem, movie);
            }
        }
        return movie;
    }

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public Movie createMovie(Movie movie) {
        try {
            movie.setId(null);
            System.out.println("Creating movie in service: " + movie.getMovieTitle());
            return movieRepository.save(movie);
        } catch (Exception e) {
            System.err.println("Error in createMovie: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public boolean deleteMovie(Integer id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }
}