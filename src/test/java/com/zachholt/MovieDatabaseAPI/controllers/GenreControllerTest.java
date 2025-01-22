package com.zachholt.MovieDatabaseAPI.controllers;

import com.zachholt.MovieDatabaseAPI.models.Genre;
import com.zachholt.MovieDatabaseAPI.services.GenreService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Genre Controller Test")
public class GenreControllerTest {

    @Mock
    GenreService genreService;

    @InjectMocks
    private GenreController subject;

    @Test
    @DisplayName("Get All Genres - Success")
    void test_getAllGenres_success() {
        //given
        List<Genre> mockGenres = new ArrayList<>();
        mockGenres.add(createTestGenre());
        when(genreService.findAllGenres()).thenReturn(mockGenres);

        //when
        ResponseEntity<List<Genre>> response = subject.getAllGenres();

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).getGenre()).isEqualTo("Action");
    }

    private Genre createTestGenre() {
        Genre genre = new Genre();
        genre.setId(1);
        genre.setGenre("Action");
        return genre;
    }
}