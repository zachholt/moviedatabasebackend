package com.zachholt.MovieDatabaseAPI.services;

import com.zachholt.MovieDatabaseAPI.models.Director;
import com.zachholt.MovieDatabaseAPI.repos.DirectorRepository;
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
@DisplayName("Director Service Test")
public class DirectorServiceTest {

    @Mock
    private DirectorRepository directorRepository;

    @InjectMocks
    private DirectorService subject;

    @Test
    @DisplayName("Get All Directors - Empty List")
    void test_getAllDirectors_empty() {
        // given
        when(directorRepository.findAll()).thenReturn(new ArrayList<>());

        // when
        List<Director> result = subject.findAllDirectors();

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Add Director")
    void test_addDirector() {
        // given
        Director mockDirector = createTestDirector();
        when(directorRepository.save(any(Director.class))).thenReturn(mockDirector);

        // when
        Director savedDirector = subject.createDirector(mockDirector);

        // then
        assertThat(savedDirector).isNotNull();
        assertThat(savedDirector.getFirstName()).isEqualTo(mockDirector.getFirstName());
        assertThat(savedDirector.getLastName()).isEqualTo(mockDirector.getLastName());
    }

 //   @Test
    @DisplayName("Update Director")
    void test_updateDirector() {
        // given
        Director mockDirector = createTestDirector();
        Director updateRequest = new Director();
        updateRequest.setId(1);
        updateRequest.setFirstName("Martin");
        updateRequest.setLastName("Scorsese");
        
        when(directorRepository.findById(1)).thenReturn(Optional.of(mockDirector));
        when(directorRepository.save(any(Director.class))).thenReturn(updateRequest);

        // when
        Director updatedDirector = subject.updateDirector(updateRequest);

        // then
        assertThat(updatedDirector).isNotNull();
        assertThat(updatedDirector.getFirstName()).isEqualTo("Martin");
        assertThat(updatedDirector.getLastName()).isEqualTo("Scorsese");
    }

    @Test
    @DisplayName("Delete Director - Success")
    void test_deleteDirector_success() {
        // given
        when(directorRepository.existsById(1)).thenReturn(true);

        // when
        boolean result = subject.deleteDirector(1);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Delete Director - Failed")
    void test_deleteDirector_failed() {
        // given
        when(directorRepository.existsById(999)).thenReturn(false);

        // when
        boolean result = subject.deleteDirector(999);

        // then
        assertThat(result).isFalse();
    }

    private Director createTestDirector() {
        Director director = new Director();
        director.setId(1);
        director.setFirstName("Steven");
        director.setLastName("Spielberg");
        return director;
    }
} 