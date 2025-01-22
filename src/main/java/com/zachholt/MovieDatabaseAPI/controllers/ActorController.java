package com.zachholt.MovieDatabaseAPI.controllers;

import com.zachholt.MovieDatabaseAPI.models.Actor;
import com.zachholt.MovieDatabaseAPI.services.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/actors")
public class ActorController {
    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/{id}")
    @Operation(
            description = "Returns actor by the supplied ID",
            summary = "Get a specific actor by its ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Actor.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Actor not found",
                            responseCode = "404"
                    )
            }
    )
    public ResponseEntity<Actor> getActorById(@PathVariable Integer id) {
        Actor actor = actorService.findActorById(id);
        return actor != null ? ResponseEntity.ok(actor) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    @Operation(
            description = "Returns a list of all actors",
            summary = "Get all actors",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Actor.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<Actor>> getAllActors() {
        return ResponseEntity.ok(actorService.findAllActors());
    }

    @PostMapping
    @Operation(
            description = "Creates a new actor",
            summary = "Create an actor",
            responses = {
                    @ApiResponse(
                            description = "Actor created successfully",
                            responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Actor.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Invalid input",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Internal server error",
                            responseCode = "500"
                    )
            }
    )
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
            System.out.println("Received actor data: " + actor.getFirstName() + " " + actor.getLastName());
            Actor createdActor = actorService.createActor(actor);
            return new ResponseEntity<>(createdActor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            description = "Updates an existing actor",
            summary = "Update an actor",
            responses = {
                    @ApiResponse(
                            description = "Actor updated successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Actor.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Actor not found",
                            responseCode = "404"
                    )
            }
    )
    public ResponseEntity<Actor> updateActor(@PathVariable Integer id, @RequestBody Actor actor) {
        actor.setId(id);
        Actor updatedActor = actorService.updateActor(actor);
        return updatedActor != null ? ResponseEntity.ok(updatedActor) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(
            description = "Deletes an actor by ID",
            summary = "Delete an actor",
            responses = {
                    @ApiResponse(
                            description = "Actor deleted successfully",
                            responseCode = "204"
                    ),
                    @ApiResponse(
                            description = "Actor not found",
                            responseCode = "404"
                    )
            }
    )
    public ResponseEntity<Actor> deleteActor(@PathVariable Integer id) {
        return actorService.deleteActor(id) ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
} 