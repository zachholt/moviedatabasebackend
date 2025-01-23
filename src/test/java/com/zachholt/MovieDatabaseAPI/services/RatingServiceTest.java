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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Rating Service Test")
public class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingService subject;

    @Test
    @DisplayName("Find Rating By Id - Success")
    void test_findRatingById_success() {
        // given
        Rating mockRating = createTestRating();
        when(ratingRepository.findById(1)).thenReturn(Optional.of(mockRating));

        // when
        Rating result = subject.findRatingById(1);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getRating()).isEqualTo("PG-13");
        assertThat(result.getDescription()).isEqualTo("Parental Guidance for children under 13");
    }

    @Test
    @DisplayName("Find Rating By Id - Not Found")
    void test_findRatingById_notFound() {
        // given
        when(ratingRepository.findById(999)).thenReturn(Optional.empty());

        // when
        Rating result = subject.findRatingById(999);

        // then
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Find Rating By Id - Null Id")
    void test_findRatingById_nullId() {
        // when
        Rating result = subject.findRatingById(null);

        // then
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Get All Ratings - Empty List")
    void test_getAllRatings_empty() {
        // given
        when(ratingRepository.findAll()).thenReturn(new ArrayList<>());

        // when
        List<Rating> result = subject.findAllRatings();

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Get All Ratings - With Data")
    void test_getAllRatings_withData() {
        // given
        List<Rating> ratings = List.of(createTestRating());
        when(ratingRepository.findAll()).thenReturn(ratings);

        // when
        List<Rating> result = subject.findAllRatings();

        // then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getRating()).isEqualTo("PG-13");
    }

    private Rating createTestRating() {
        Rating rating = new Rating();
        rating.setId(1);
        rating.setRating("PG-13");
        rating.setDescription("Parental Guidance for children under 13");
        return rating;
    }
} 