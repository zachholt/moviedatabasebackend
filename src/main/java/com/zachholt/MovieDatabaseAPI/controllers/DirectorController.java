package com.zachholt.MovieDatabaseAPI.controllers;

import com.zachholt.MovieDatabaseAPI.models.Director;
import com.zachholt.MovieDatabaseAPI.services.DirectorService;
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
@RequestMapping("/api/v1/directors")
public class DirectorController {
    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("/{id}")
    @Operation(
            description = "Returns director by the supplied ID",
            summary = "Get a specific director by its ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Director.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Director not found",
                            responseCode = "404"
                    )
            }
    )
    public ResponseEntity<Director> getDirectorById(@PathVariable Integer id) {
        Director director = directorService.findDirectorById(id);
        return director != null ? ResponseEntity.ok(director) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    @Operation(
            description = "Returns a list of all directors",
            summary = "Get all directors",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Director.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<Director>> getAllDirectors() {
        return ResponseEntity.ok(directorService.findAllDirectors());
    }

    @PostMapping
    @Operation(
            description = "Creates a new director",
            summary = "Create a director",
            responses = {
                    @ApiResponse(
                            description = "Director created successfully",
                            responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Director.class)
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
    public ResponseEntity<Director> createDirector(@RequestBody Director director) {
        try {
            System.out.println("Received director data: " + director.getFirstName() + " " + director.getLastName());
            Director createdDirector = directorService.createDirector(director);
            return new ResponseEntity<>(createdDirector, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error creating director: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(
            description = "Updates an existing director",
            summary = "Update a director",
            responses = {
                    @ApiResponse(
                            description = "Director updated successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Director.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Director not found",
                            responseCode = "404"
                    )
            }
    )
    public ResponseEntity<Director> updateDirector(@PathVariable Integer id, @RequestBody Director director) {
        director.setId(id);
        Director updatedDirector = directorService.updateDirector(director);
        return updatedDirector != null ? ResponseEntity.ok(updatedDirector) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(
            description = "Deletes a director by ID",
            summary = "Delete a director",
            responses = {
                    @ApiResponse(
                            description = "Director deleted successfully",
                            responseCode = "204"
                    ),
                    @ApiResponse(
                            description = "Director not found",
                            responseCode = "404"
                    )
            }
    )
    public ResponseEntity<Director> deleteDirector(@PathVariable Integer id) {
        return directorService.deleteDirector(id) ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}