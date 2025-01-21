package com.zachholt.MovieDatabaseAPI.controllers;

import com.zachholt.MovieDatabaseAPI.models.Genre;
import com.zachholt.MovieDatabaseAPI.services.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/genres")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    @Operation(
            description = "Returns a list of all genres",
            summary = "Get all genres",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Genre.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<Genre>> getAllGenres() {
        return ResponseEntity.ok(genreService.findAllGenres());
    }

} 