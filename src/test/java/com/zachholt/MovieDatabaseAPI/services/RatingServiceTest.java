package com.zachholt.MovieDatabaseAPI.services;

import com.zachholt.MovieDatabaseAPI.models.Rating;
import com.zachholt.MovieDatabaseAPI.repos.RatingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Rating Service Test")
public class RatingServiceTest {

    @Mock
    RatingRepository ratingRepository;

    @InjectMocks
    private RatingService subject;

    @Test
    @DisplayName("Get All Ratings - Empty List")
    void test_getAllRatings_empty() {
        //given
        when(ratingRepository.findAll()).thenReturn(new ArrayList<>());

        //when
        List<Rating> result = subject.findAllRatings();

        //then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Get All Ratings - With Data")
    void test_getAllRatings_withData() {
        //given
        List<Rating> mockRatings = new ArrayList<>();
        mockRatings.add(createTestRating());
        when(ratingRepository.findAll()).thenReturn(mockRatings);

        //when
        List<Rating> result = subject.findAllRatings();

        //then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getRating()).isEqualTo("PG-13");
    }

    private Rating createTestRating() {
        Rating rating = new Rating();
        rating.setId(1);
        rating.setRating("PG-13");
        return rating;
    }
} 