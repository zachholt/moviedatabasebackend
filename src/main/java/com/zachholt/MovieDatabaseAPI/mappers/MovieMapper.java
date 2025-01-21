package com.zachholt.MovieDatabaseAPI.mappers;

import com.zachholt.MovieDatabaseAPI.models.Movie;
import com.zachholt.MovieDatabaseAPI.models.movieExternal.MovieDatabaseItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "overview", expression = "java(getOverviewFromResults(movieDatabaseItem))")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", ignore = true)
    @Mapping(target = "movieTitle", ignore = true)
    @Mapping(target = "movieLength", ignore = true)
    @Mapping(target = "releaseDate", ignore = true)
    @Mapping(target = "trailerUrl", ignore = true)
    @Mapping(target = "director", ignore = true)
    @Mapping(target = "genre", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "actors", ignore = true)
    Movie updateMovieWithOverview(MovieDatabaseItem movieDatabaseItem, @MappingTarget Movie movie);

    default String getOverviewFromResults(MovieDatabaseItem movieDatabaseItem) {
        if (movieDatabaseItem != null && movieDatabaseItem.getResults() != null && !movieDatabaseItem.getResults().isEmpty()) {
            return movieDatabaseItem.getResults().get(0).getOverview();
        }
        return null;
    }
}
