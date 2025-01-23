package com.zachholt.MovieDatabaseAPI.services;

import com.zachholt.MovieDatabaseAPI.clients.MovieClient;
import com.zachholt.MovieDatabaseAPI.mappers.MovieMapper;
import com.zachholt.MovieDatabaseAPI.models.Movie;
import com.zachholt.MovieDatabaseAPI.models.Director;
import com.zachholt.MovieDatabaseAPI.models.Genre;
import com.zachholt.MovieDatabaseAPI.models.movieExternal.MovieDatabaseItem;
import com.zachholt.MovieDatabaseAPI.repos.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Movie Service Test")
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieMapper movieMapper;

    @Mock
    private MovieClient movieClient;

    @InjectMocks
    private MovieService subject;

    @Test
    @DisplayName("Find Movie By Id - Success with Overview")
    void test_findMovieById_successWithOverview() {
        // given
        Movie mockMovie = createTestMovie();
        MovieDatabaseItem movieDatabaseItem = new MovieDatabaseItem();
        Movie movieWithOverview = createTestMovie();
        movieWithOverview.setOverview("Test overview");

        when(movieRepository.findById(1)).thenReturn(Optional.of(mockMovie));
        when(movieClient.searchMovie("Test Movie")).thenReturn(Mono.just(movieDatabaseItem));
        when(movieMapper.updateMovieWithOverview(movieDatabaseItem, mockMovie)).thenReturn(movieWithOverview);

        // when
        Movie result = subject.findMovieById(1);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getMovieTitle()).isEqualTo("Test Movie");
        assertThat(result.getOverview()).isEqualTo("Test overview");
        assertThat(result.getDirector().getFirstName()).isEqualTo("Steven");
        verify(movieRepository).findById(1);
        verify(movieClient).searchMovie("Test Movie");
        verify(movieMapper).updateMovieWithOverview(movieDatabaseItem, mockMovie);
    }

    @Test
    @DisplayName("Find Movie By Id - Success without Overview")
    void test_findMovieById_successWithoutOverview() {
        // given
        Movie mockMovie = createTestMovie();
        when(movieRepository.findById(1)).thenReturn(Optional.of(mockMovie));
        when(movieClient.searchMovie("Test Movie")).thenReturn(Mono.empty());

        // when
        Movie result = subject.findMovieById(1);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getMovieTitle()).isEqualTo("Test Movie");
        assertThat(result.getOverview()).isNull();
    }

    @Test
    @DisplayName("Find Movie By Id - Not Found")
    void test_findMovieById_notFound() {
        // given
        when(movieRepository.findById(999)).thenReturn(Optional.empty());

        // when
        Movie result = subject.findMovieById(999);

        // then
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Find Movie By Id - Null Id")
    void test_findMovieById_nullId() {
        // when
        Movie result = subject.findMovieById(null);

        // then
        assertThat(result).isNull();
    }

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