package com.zachholt.MovieDatabaseAPI.services;

import com.zachholt.MovieDatabaseAPI.models.Movie;
import com.zachholt.MovieDatabaseAPI.models.Director;
import com.zachholt.MovieDatabaseAPI.models.Genre;
import com.zachholt.MovieDatabaseAPI.repos.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Movie Service Test")
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService subject;

    @Test
    @DisplayName("Get All Movies - Empty List")
    void test_getAllMovies_empty() {
        // given
        when(movieRepository.findAll()).thenReturn(new ArrayList<>());

        // when
        List<Movie> result = subject.findAllMovies();

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Add and Get Movie")
    void test_addAndGetMovie() {
        // given
        Movie newMovie = createTestMovie();
        when(movieRepository.save(any(Movie.class))).thenReturn(newMovie);
        when(movieRepository.findById(any(Integer.class))).thenReturn(Optional.of(newMovie));

        // when
        Movie savedMovie = subject.createMovie(newMovie);

        // then
        assertThat(savedMovie.getId()).isNotNull();
        assertThat(savedMovie.getMovieTitle()).isEqualTo(newMovie.getMovieTitle());
        assertThat(savedMovie.getDirector()).isEqualTo(newMovie.getDirector());
        assertThat(savedMovie.getReleaseDate()).isEqualTo(newMovie.getReleaseDate());
        assertThat(savedMovie.getGenre()).isEqualTo(newMovie.getGenre());

        // when
        Movie retrievedMovie = subject.findMovieById(savedMovie.getId());

        // then
        assertThat(retrievedMovie).isNotNull();
        assertThat(retrievedMovie.getId()).isEqualTo(savedMovie.getId());
    }

    @Test
    @DisplayName("Delete Movie")
    void test_deleteMovie() {
        // given
        Movie movie = createTestMovie();
        when(movieRepository.findById(any(Integer.class))).thenReturn(Optional.of(movie));

        // when
        boolean deleteResult = subject.deleteMovie(movie.getId());

        // then
        assertThat(deleteResult).isTrue();
    }

    @Test
    @DisplayName("Delete Movie - Not Found")
    void test_deleteMovie_notFound() {
        // given
        when(movieRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        // when
        boolean result = subject.deleteMovie(1);

        // then
        assertThat(result).isFalse();
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