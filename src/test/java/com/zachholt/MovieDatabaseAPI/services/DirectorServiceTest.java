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
        Director newDirector = createTestDirector();
        newDirector.setId(null);  // New director shouldn't have ID
        
        when(directorRepository.save(any(Director.class))).thenAnswer(invocation -> {
            Director savedDirector = invocation.getArgument(0);
            savedDirector.setId(1);  // Simulate DB setting ID
            return savedDirector;
        });

        // when
        Director savedDirector = subject.createDirector(newDirector);

        // then
        assertThat(savedDirector).isNotNull();
        assertThat(savedDirector.getId()).isEqualTo(1);
        assertThat(savedDirector.getFirstName()).isEqualTo(newDirector.getFirstName());
        assertThat(savedDirector.getLastName()).isEqualTo(newDirector.getLastName());
        verify(directorRepository).save(any(Director.class));
    }

    @Test
    @DisplayName("Update Director")
    void test_updateDirector() {
        // given
        Director existingDirector = createTestDirector();
        Director updateRequest = new Director();
        updateRequest.setId(1);
        updateRequest.setFirstName("Martin");
        updateRequest.setLastName("Scorsese");
        
        // Mock both the find and save operations
        when(directorRepository.findById(1)).thenReturn(Optional.of(existingDirector));
        when(directorRepository.save(any(Director.class))).thenAnswer(invocation -> {
            Director savedDirector = invocation.getArgument(0);
            savedDirector.setId(1);  // Ensure ID is set
            return savedDirector;
        });

        // when
        Director updatedDirector = subject.updateDirector(updateRequest);

        // then
        assertThat(updatedDirector).isNotNull();
        assertThat(updatedDirector.getFirstName()).isEqualTo("Martin");
        assertThat(updatedDirector.getLastName()).isEqualTo("Scorsese");
        verify(directorRepository).findById(1);
        verify(directorRepository).save(any(Director.class));
    }

    @Test
    @DisplayName("Delete Director")
    void test_deleteDirector() {
        // given
        when(directorRepository.existsById(1)).thenReturn(true);

        // when
        boolean deleteResult = subject.deleteDirector(1);

        // then
        assertThat(deleteResult).isTrue();
        verify(directorRepository).deleteById(1);
    }

    @Test
    @DisplayName("Delete Director - Not Found")
    void test_deleteDirector_notFound() {
        // given
        when(directorRepository.existsById(999)).thenReturn(false);

        // when
        boolean result = subject.deleteDirector(999);

        // then
        assertThat(result).isFalse();
        verify(directorRepository).existsById(999);
        verify(directorRepository, never()).deleteById(any());
    }

    private Director createTestDirector() {
        Director director = new Director();
        director.setId(1);
        director.setFirstName("Steven");
        director.setLastName("Spielberg");
        return director;
    }
} 