package com.zachholt.MovieDatabaseAPI.controllers;

import com.zachholt.MovieDatabaseAPI.models.Actor;
import com.zachholt.MovieDatabaseAPI.services.ActorService;
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
@DisplayName("Actor Controller Test")
public class ActorControllerTest {

    @Mock
    private ActorService actorService;

    @InjectMocks
    private ActorController subject;

    @Test
    @DisplayName("Get All Actors")
    void test_getAllActors() {
        // given
        List<Actor> actors = new ArrayList<>();
        actors.add(createTestActor());
        when(actorService.findAllActors()).thenReturn(actors);

        // when
        ResponseEntity<List<Actor>> response = subject.getAllActors();

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(1);
    }

    @Test
    @DisplayName("Get Actor By Id")
    void test_getActorById() {
        // given
        Actor actor = createTestActor();
        when(actorService.findActorById(any(Integer.class))).thenReturn(actor);

        // when
        ResponseEntity<Actor> response = subject.getActorById(1);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getFirstName()).isEqualTo(actor.getFirstName());
    }

    @Test
    @DisplayName("Create Actor")
    void test_createActor() {
        // given
        Actor actor = createTestActor();
        when(actorService.createActor(any())).thenReturn(actor);

        // when
        ResponseEntity<Actor> response = subject.createActor(actor);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getFirstName()).isEqualTo(actor.getFirstName());
    }

    @Test
    @DisplayName("Update Actor")
    void test_updateActor() {
        // given
        Actor actor = createTestActor();
        when(actorService.updateActor(any())).thenReturn(actor);

        // when
        ResponseEntity<Actor> response = subject.updateActor(1, actor);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getFirstName()).isEqualTo(actor.getFirstName());
    }

    @Test
    @DisplayName("Delete Actor")
    void test_deleteActor() {
        // given
        when(actorService.deleteActor(any(Integer.class))).thenReturn(true);

        // when
        ResponseEntity<?> response = subject.deleteActor(1);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    private Actor createTestActor() {
        Actor actor = new Actor();
        actor.setId(1);
        actor.setFirstName("John");
        actor.setLastName("Doe");
        return actor;
    }
} 