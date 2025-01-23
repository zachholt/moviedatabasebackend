package com.zachholt.MovieDatabaseAPI.mappers;

import com.zachholt.MovieDatabaseAPI.models.Movie;
import com.zachholt.MovieDatabaseAPI.models.movieExternal.MovieDatabaseItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Movie Mapper Test")
class  MovieMapperTest {

    private final MovieMapper movieMapper = Mappers.getMapper(MovieMapper.class);

    @Test
    @DisplayName("Update Movie With Overview - Success")
    void test_updateMovieWithOverview_success() {
        // given
        Movie movie = createTestMovie();
        MovieDatabaseItem movieDatabaseItem = createTestMovieDatabaseItem("Test movie overview");

        // when
        Movie updatedMovie = movieMapper.updateMovieWithOverview(movieDatabaseItem, movie);

        // then
        assertThat(updatedMovie).isNotNull();
        assertThat(updatedMovie.getOverview()).isEqualTo("Test movie overview");
        assertThat(updatedMovie.getId()).isEqualTo(movie.getId());
        assertThat(updatedMovie.getMovieTitle()).isEqualTo(movie.getMovieTitle());
        assertThat(updatedMovie.getDirector()).isEqualTo(movie.getDirector());
        assertThat(updatedMovie.getGenre()).isEqualTo(movie.getGenre());
        assertThat(updatedMovie.getRating()).isEqualTo(movie.getRating());
        assertThat(updatedMovie.getActors()).isEqualTo(movie.getActors());
    }

    @Test
    @DisplayName("Update Movie With Overview - Null MovieDatabaseItem")
    void test_updateMovieWithOverview_nullMovieDatabaseItem() {
        // given
        Movie movie = createTestMovie();

        // when
        Movie updatedMovie = movieMapper.updateMovieWithOverview(null, movie);

        // then
        assertThat(updatedMovie).isNotNull();
        assertThat(updatedMovie.getOverview()).isNull();
        assertThat(updatedMovie.getId()).isEqualTo(movie.getId());
        assertThat(updatedMovie.getMovieTitle()).isEqualTo(movie.getMovieTitle());
    }

    @Test
    @DisplayName("Update Movie With Overview - Empty Results")
    void test_updateMovieWithOverview_emptyResults() {
        // given
        Movie movie = createTestMovie();
        MovieDatabaseItem movieDatabaseItem = new MovieDatabaseItem();
        movieDatabaseItem.setResults(new ArrayList<>());

        // when
        Movie updatedMovie = movieMapper.updateMovieWithOverview(movieDatabaseItem, movie);

        // then
        assertThat(updatedMovie).isNotNull();
        assertThat(updatedMovie.getOverview()).isNull();
    }

    @Test
    @DisplayName("Get Overview From Results - Success")
    void test_getOverviewFromResults_success() {
        // given
        MovieDatabaseItem movieDatabaseItem = createTestMovieDatabaseItem("Test movie overview");

        // when
        String overview = movieMapper.getOverviewFromResults(movieDatabaseItem);

        // then
        assertThat(overview).isEqualTo("Test movie overview");
    }

    @Test
    @DisplayName("Get Overview From Results - Null MovieDatabaseItem")
    void test_getOverviewFromResults_nullMovieDatabaseItem() {
        // when
        String overview = movieMapper.getOverviewFromResults(null);

        // then
        assertThat(overview).isNull();
    }

    @Test
    @DisplayName("Get Overview From Results - Null Results")
    void test_getOverviewFromResults_nullResults() {
        // given
        MovieDatabaseItem movieDatabaseItem = new MovieDatabaseItem();

        // when
        String overview = movieMapper.getOverviewFromResults(movieDatabaseItem);

        // then
        assertThat(overview).isNull();
    }

    @Test
    @DisplayName("Get Overview From Results - Empty Results")
    void test_getOverviewFromResults_emptyResults() {
        // given
        MovieDatabaseItem movieDatabaseItem = new MovieDatabaseItem();
        movieDatabaseItem.setResults(new ArrayList<>());

        // when
        String overview = movieMapper.getOverviewFromResults(movieDatabaseItem);

        // then
        assertThat(overview).isNull();
    }

    private Movie createTestMovie() {
        Movie movie = new Movie();
        movie.setId(1);
        movie.setMovieTitle("Test Movie");
        return movie;
    }

    private MovieDatabaseItem createTestMovieDatabaseItem(String overview) {
        MovieDatabaseItem movieDatabaseItem = new MovieDatabaseItem();
        MovieDatabaseItem.Result result = new MovieDatabaseItem.Result();
        result.setOverview(overview);
        List<MovieDatabaseItem.Result> results = new ArrayList<>();
        results.add(result);
        movieDatabaseItem.setResults(results);
        return movieDatabaseItem;
    }
} 