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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

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
    @DisplayName("Add Movie")
    void test_addMovie() {
        // given
        Movie mockMovie = createTestMovie();
        when(movieRepository.save(any(Movie.class))).thenReturn(mockMovie);

        // when
        Movie savedMovie = subject.createMovie(mockMovie);

        // then
        assertThat(savedMovie).isNotNull();
        assertThat(savedMovie.getMovieTitle()).isEqualTo(mockMovie.getMovieTitle());
        assertThat(savedMovie.getDirector()).isEqualTo(mockMovie.getDirector());
        assertThat(savedMovie.getReleaseDate()).isEqualTo(mockMovie.getReleaseDate());
        assertThat(savedMovie.getGenre()).isEqualTo(mockMovie.getGenre());
    }

    @Test
    @DisplayName("Delete Movie - Success")
    void test_deleteMovie_success() {
        // given
        when(movieRepository.existsById(1)).thenReturn(true);

        // when
        boolean result = subject.deleteMovie(1);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Delete Movie - Failed")
    void test_deleteMovie_failed() {
        // given
        when(movieRepository.existsById(999)).thenReturn(false);

        // when
        boolean result = subject.deleteMovie(999);

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