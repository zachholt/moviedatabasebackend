package com.zachholt.MovieDatabaseAPI.controllers;

import com.zachholt.MovieDatabaseAPI.models.Rating;
import com.zachholt.MovieDatabaseAPI.services.RatingService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Rating Controller Test")
public class RatingControllerTest {

    @Mock
    RatingService ratingService;

    @InjectMocks
    private RatingController subject;

    @Test
    @DisplayName("Get All Ratings - Success")
    void test_getAllRatings_success() {
        //given
        List<Rating> mockRatings = new ArrayList<>();
        mockRatings.add(createTestRating());
        when(ratingService.findAllRatings()).thenReturn(mockRatings);

        //when
        ResponseEntity<List<Rating>> response = subject.getAllRatings();

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).getRating()).isEqualTo("PG-13");
    }

    private Rating createTestRating() {
        Rating rating = new Rating();
        rating.setId(1);
        rating.setRating("PG-13");
        return rating;
    }
} 