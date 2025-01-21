package com.zachholt.MovieDatabaseAPI.controllers;

import com.zachholt.MovieDatabaseAPI.models.Movie;
import com.zachholt.MovieDatabaseAPI.models.Director;
import com.zachholt.MovieDatabaseAPI.models.Genre;
import com.zachholt.MovieDatabaseAPI.services.MovieService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Movie Controller Test")
public class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController subject;

    @Test
    @DisplayName("Get All Movies")
    void test_getAllMovies() {
        // given
        List<Movie> movies = new ArrayList<>();
        movies.add(createTestMovie());
        when(movieService.findAllMovies()).thenReturn(movies);

        // when
        ResponseEntity<List<Movie>> response = subject.getAllMovies();

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(1);
    }

    @Test
    @DisplayName("Get Movie By Id")
    void test_getMovieById() {
        // given
        Movie movie = createTestMovie();
        when(movieService.findMovieById(any(Integer.class))).thenReturn(movie);

        // when
        ResponseEntity<Movie> response = subject.getMovieById(1);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMovieTitle()).isEqualTo(movie.getMovieTitle());
    }

    @Test
    @DisplayName("Create Movie")
    void test_createMovie() {
        // given
        Movie movie = createTestMovie();
        when(movieService.createMovie(any())).thenReturn(movie);

        // when
        ResponseEntity<Movie> response = subject.createMovie(movie);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMovieTitle()).isEqualTo(movie.getMovieTitle());
    }

    @Test
    @DisplayName("Delete Movie")
    void test_deleteMovie() {
        // given
        when(movieService.deleteMovie(any(Integer.class))).thenReturn(true);

        // when
        ResponseEntity<?> response = subject.deleteMovie(1);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    private Movie createTestMovie() {
        Movie movie = new Movie();
        movie.setId(1);
        movie.setMovieTitle("Test Movie");
        
        Director director = new Director();
        director.setId(1);
        director.setFirstName("Steven");
        director.setLastName("Spielberg");
        movie.setDirector(director);
        
        movie.setReleaseDate("2024");
        
        Genre genre = new Genre();
        genre.setId(1);
        genre.setGenre("Action");
        movie.setGenre(genre);
        
        return movie;
    }
} 