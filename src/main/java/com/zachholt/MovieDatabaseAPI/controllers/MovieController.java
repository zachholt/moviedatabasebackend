package com.zachholt.MovieDatabaseAPI.controllers;

import com.zachholt.MovieDatabaseAPI.models.Movie;
import com.zachholt.MovieDatabaseAPI.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    @Operation(
            description = "Returns movie by the supplied ID",
            summary = "Get a specific movie by its ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Movie.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Movie not found",
                            responseCode = "404"
                    )
            }
    )
    public ResponseEntity<Movie> getMovieById(@PathVariable Integer id) {
        Movie movie = movieService.findMovieById(id);
        return movie != null ? ResponseEntity.ok(movie) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    @Operation(
            description = "Returns a list of all movies",
            summary = "Get all movies",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Movie.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.findAllMovies());
    }

    @PostMapping
    @Operation(
            description = "Creates a new movie",
            summary = "Create a movie",
            responses = {
                    @ApiResponse(
                            description = "Movie created successfully",
                            responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Movie.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Invalid input",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Internal server error",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Map.class)
                            )
                    )
            }
    )
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
            System.out.println("Received movie data: " + movie.getMovieTitle());
            Movie createdMovie = movieService.createMovie(movie);
            return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    @Operation(
            description = "Deletes a movie by ID",
            summary = "Delete a movie",
            responses = {
                    @ApiResponse(
                            description = "Movie deleted successfully",
                            responseCode = "204"
                    ),
                    @ApiResponse(
                            description = "Movie not found",
                            responseCode = "404"
                    )
            }
    )
    public ResponseEntity<Movie> deleteMovie(@PathVariable Integer id) {
        return movieService.deleteMovie(id) ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}