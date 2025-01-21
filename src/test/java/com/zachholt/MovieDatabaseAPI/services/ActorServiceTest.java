package com.zachholt.MovieDatabaseAPI.services;

import com.zachholt.MovieDatabaseAPI.models.Actor;
import com.zachholt.MovieDatabaseAPI.repos.ActorRepository;
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
@DisplayName("Actor Service Test")
public class ActorServiceTest {

    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private ActorService subject;

    @Test
    @DisplayName("Get All Actors - Empty List")
    void test_getAllActors_empty() {
        // given
        when(actorRepository.findAll()).thenReturn(new ArrayList<>());

        // when
        List<Actor> result = subject.findAllActors();

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Add Actor")
    void test_addActor() {
        // given
        Actor newActor = createTestActor();
        newActor.setId(null);  // New actor shouldn't have ID
        
        when(actorRepository.save(any(Actor.class))).thenAnswer(invocation -> {
            Actor savedActor = invocation.getArgument(0);
            savedActor.setId(1);  // Simulate DB setting ID
            return savedActor;
        });

        // when
        Actor savedActor = subject.createActor(newActor);

        // then
        assertThat(savedActor).isNotNull();
        assertThat(savedActor.getId()).isEqualTo(1);
        assertThat(savedActor.getFirstName()).isEqualTo(newActor.getFirstName());
        assertThat(savedActor.getLastName()).isEqualTo(newActor.getLastName());
        verify(actorRepository).save(any(Actor.class));
    }

    @Test
    @DisplayName("Update Actor")
    void test_updateActor() {
        // given
        Actor existingActor = createTestActor();
        Actor updateRequest = new Actor();
        updateRequest.setId(1);
        updateRequest.setFirstName("Jane");
        updateRequest.setLastName("Smith");
        
        // Mock both the find and save operations
        when(actorRepository.findById(1)).thenReturn(Optional.of(existingActor));
        when(actorRepository.save(any(Actor.class))).thenAnswer(invocation -> {
            Actor savedActor = invocation.getArgument(0);
            savedActor.setId(1);  // Ensure ID is set
            return savedActor;
        });

        // when
        Actor updatedActor = subject.updateActor(updateRequest);

        // then
        assertThat(updatedActor).isNotNull();
        assertThat(updatedActor.getFirstName()).isEqualTo("Jane");
        assertThat(updatedActor.getLastName()).isEqualTo("Smith");
        verify(actorRepository).findById(1);
        verify(actorRepository).save(any(Actor.class));
    }

    @Test
    @DisplayName("Delete Actor")
    void test_deleteActor() {
        // given
        Actor actor = createTestActor();
        when(actorRepository.existsById(1)).thenReturn(true);

        // when
        boolean deleteResult = subject.deleteActor(1);

        // then
        assertThat(deleteResult).isTrue();
        verify(actorRepository).deleteById(1);
    }

    @Test
    @DisplayName("Delete Actor - Not Found")
    void test_deleteActor_notFound() {
        // given
        when(actorRepository.existsById(999)).thenReturn(false);

        // when
        boolean result = subject.deleteActor(999);

        // then
        assertThat(result).isFalse();
        verify(actorRepository).existsById(999);
        verify(actorRepository, never()).deleteById(any());
    }

    private Actor createTestActor() {
        Actor actor = new Actor();
        actor.setId(1);
        actor.setFirstName("John");
        actor.setLastName("Doe");
        return actor;
    }
} 