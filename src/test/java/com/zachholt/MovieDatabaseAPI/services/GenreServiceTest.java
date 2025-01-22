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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Genre Service Test")
public class GenreServiceTest {

    @Mock
    GenreRepository genreRepository;

    @InjectMocks
    private GenreService subject;

    @Test
    @DisplayName("Get All Genres - Empty List")
    void test_getAllGenres_empty() {
        //given
        when(genreRepository.findAll()).thenReturn(new ArrayList<>());

        //when
        List<Genre> result = subject.findAllGenres();

        //then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Get All Genres - With Data")
    void test_getAllGenres_withData() {
        //given
        List<Genre> mockGenres = new ArrayList<>();
        mockGenres.add(createTestGenre());
        when(genreRepository.findAll()).thenReturn(mockGenres);

        //when
        List<Genre> result = subject.findAllGenres();

        //then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getGenre()).isEqualTo("Action");
    }

    private Genre createTestGenre() {
        Genre genre = new Genre();
        genre.setId(1);
        genre.setGenre("Action");
        return genre;
    }
} 