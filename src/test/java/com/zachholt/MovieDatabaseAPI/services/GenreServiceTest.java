package com.zachholt.MovieDatabaseAPI.services;

import com.zachholt.MovieDatabaseAPI.models.Genre;
import com.zachholt.MovieDatabaseAPI.repos.GenreRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Genre Service Test")
public class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService subject;

    @Test
    @DisplayName("Find Genre By Id - Success")
    void test_findGenreById_success() {
        // given
        Genre mockGenre = createTestGenre();
        when(genreRepository.findById(1)).thenReturn(Optional.of(mockGenre));

        // when
        Genre result = subject.findGenreById(1);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getGenre()).isEqualTo("Action");
        assertThat(result.getDescription()).isEqualTo("Action movies");
    }

    @Test
    @DisplayName("Find Genre By Id - Not Found")
    void test_findGenreById_notFound() {
        // given
        when(genreRepository.findById(999)).thenReturn(Optional.empty());

        // when
        Genre result = subject.findGenreById(999);

        // then
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Find Genre By Id - Null Id")
    void test_findGenreById_nullId() {
        // when
        Genre result = subject.findGenreById(null);

        // then
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Get All Genres - Empty List")
    void test_getAllGenres_empty() {
        // given
        when(genreRepository.findAll()).thenReturn(new ArrayList<>());

        // when
        List<Genre> result = subject.findAllGenres();

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Get All Genres - With Data")
    void test_getAllGenres_withData() {
        // given
        List<Genre> genres = List.of(createTestGenre());
        when(genreRepository.findAll()).thenReturn(genres);

        // when
        List<Genre> result = subject.findAllGenres();

        // then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getGenre()).isEqualTo("Action");
    }

    private Genre createTestGenre() {
        Genre genre = new Genre();
        genre.setId(1);
        genre.setGenre("Action");
        genre.setDescription("Action movies");
        return genre;
    }
} 