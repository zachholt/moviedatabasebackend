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

@ExtendWith(MockitoExtension.class)
@DisplayName("Actor Service Test")
public class ActorServiceTest {

    @Mock
    ActorRepository actorRepository;

    @InjectMocks
    private ActorService subject;

    @Test
    @DisplayName("Get All Actors - Empty List")
    void test_getAllActors_empty() {
        //given
        when(actorRepository.findAll()).thenReturn(new ArrayList<>());

        //when
        List<Actor> result = subject.findAllActors();

        //then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Add Actor")
    void test_addActor() {
        //given
        Actor mockActor = createTestActor();
        when(actorRepository.save(any(Actor.class))).thenReturn(mockActor);

        //when
        Actor savedActor = subject.createActor(mockActor);

        //then
        assertThat(savedActor).isNotNull();
        assertThat(savedActor.getFirstName()).isEqualTo(mockActor.getFirstName());
        assertThat(savedActor.getLastName()).isEqualTo(mockActor.getLastName());
    }

 //   @Test
    @DisplayName("Update Actor")
    void test_updateActor() {
        //given
        Actor mockActor = createTestActor();
        Actor updateRequest = new Actor();
        updateRequest.setId(1);
        updateRequest.setFirstName("Jane");
        updateRequest.setLastName("Smith");
        
        when(actorRepository.findById(1)).thenReturn(Optional.of(mockActor));
        when(actorRepository.save(any(Actor.class))).thenReturn(updateRequest);

        //when
        Actor updatedActor = subject.updateActor(updateRequest);

        //then
        assertThat(updatedActor).isNotNull();
        assertThat(updatedActor.getFirstName()).isEqualTo("Jane");
        assertThat(updatedActor.getLastName()).isEqualTo("Smith");
    }

    @Test
    @DisplayName("Delete Actor - Success")
    void test_deleteActor_success() {
        //given
        when(actorRepository.existsById(1)).thenReturn(true);

        //when
        boolean result = subject.deleteActor(1);

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Delete Actor - Failed")
    void test_deleteActor_failed() {
        //given
        when(actorRepository.existsById(999)).thenReturn(false);

        //when
        boolean result = subject.deleteActor(999);

        //then
        assertThat(result).isFalse();
    }

    private Actor createTestActor() {
        Actor actor = new Actor();
        actor.setId(1);
        actor.setFirstName("John");
        actor.setLastName("Doe");
        return actor;
    }
} 