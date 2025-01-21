package com.zachholt.MovieDatabaseAPI.models.movieExternal;

import java.util.List;

public class MovieResponse {
    private List<MovieDatabaseItem> data;

    public List<MovieDatabaseItem> getData() {
        return data;
    }

    public MovieResponse setData(List<MovieDatabaseItem> data) {
        this.data = data;
        return this;
    }
}