package com.zachholt.MovieDatabaseAPI.controllers;

import com.zachholt.MovieDatabaseAPI.models.Director;
import com.zachholt.MovieDatabaseAPI.services.DirectorService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Director Controller Test")
public class DirectorControllerTest {

    @Mock
    private DirectorService directorService;

    @InjectMocks
    private DirectorController subject;

    @Test
    @DisplayName("Get All Directors")
    void test_getAllDirectors() {
        // given
        List<Director> directors = new ArrayList<>();
        directors.add(createTestDirector());
        when(directorService.findAllDirectors()).thenReturn(directors);

        // when
        ResponseEntity<List<Director>> response = subject.getAllDirectors();

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(1);
    }

    @Test
    @DisplayName("Get Director By Id")
    void test_getDirectorById() {
        // given
        Director director = createTestDirector();
        when(directorService.findDirectorById(any(Integer.class))).thenReturn(director);

        // when
        ResponseEntity<Director> response = subject.getDirectorById(1);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getFirstName()).isEqualTo(director.getFirstName());
    }

    @Test
    @DisplayName("Create Director")
    void test_createDirector() {
        // given
        Director director = createTestDirector();
        when(directorService.createDirector(any())).thenReturn(director);

        // when
        ResponseEntity<Director> response = subject.createDirector(director);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getFirstName()).isEqualTo(director.getFirstName());
    }

    @Test
    @DisplayName("Update Director")
    void test_updateDirector() {
        // given
        Director director = createTestDirector();
        when(directorService.updateDirector(any())).thenReturn(director);

        // when
        ResponseEntity<Director> response = subject.updateDirector(1, director);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getFirstName()).isEqualTo(director.getFirstName());
    }

    @Test
    @DisplayName("Delete Director")
    void test_deleteDirector() {
        // given
        when(directorService.deleteDirector(any(Integer.class))).thenReturn(true);

        // when
        ResponseEntity<?> response = subject.deleteDirector(1);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    private Director createTestDirector() {
        Director director = new Director();
        director.setId(1);
        director.setFirstName("Steven");
        director.setLastName("Spielberg");
        return director;
    }
} 