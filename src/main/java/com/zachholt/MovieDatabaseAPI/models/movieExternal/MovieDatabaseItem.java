package com.zachholt.MovieDatabaseAPI.models.movieExternal;

import java.util.List;

public class MovieDatabaseItem {
    private Integer page;
    private List<Result> results;
    private Integer totalPages;
    private Integer totalResults;

    public Integer getPage() {
        return page;
    }

    public MovieDatabaseItem setPage(Integer page) {
        this.page = page;
        return this;
    }

    public List<Result> getResults() {
        return results;
    }

    public MovieDatabaseItem setResults(List<Result> results) {
        this.results = results;
        return this;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public MovieDatabaseItem setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public MovieDatabaseItem setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    public static class Result {
        private boolean adult;
        private String backdropPath;
        private List<Integer> genreIds;
        private Integer id;
        private String originalLanguage;
        private String originalTitle;
        private String overview;
        private Number popularity;
        private String posterPath;
        private String releaseDate;
        private String title;
        private Boolean video;
        private Number voteAverage;
        private Integer voteCount;



        public boolean isAdult() {
            return adult;
        }

        public Result setAdult(boolean adult) {
            this.adult = adult;
            return this;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public Result setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
            return this;
        }

        public List<Integer> getGenreIds() {
            return genreIds;
        }

        public Result setGenreIds(List<Integer> genreIds) {
            this.genreIds = genreIds;
            return this;
        }

        public Integer getId() {
            return id;
        }

        public Result setId(Integer id) {
            this.id = id;
            return this;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public Result setOriginalLanguage(String originalLanguage) {
            this.originalLanguage = originalLanguage;
            return this;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public Result setOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
            return this;
        }

        public String getOverview() {
            return overview;
        }

        public Result setOverview(String overview) {
            this.overview = overview;
            return this;
        }

        public Number getPopularity() {
            return popularity;
        }

        public Result setPopularity(Number popularity) {
            this.popularity = popularity;
            return this;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public Result setPosterPath(String posterPath) {
            this.posterPath = posterPath;
            return this;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public Result setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public Result setTitle(String title) {
            this.title = title;
            return this;
        }

        public boolean isVideo() {
            return video;
        }

        public Result setVideo(boolean video) {
            this.video = video;
            return this;
        }

        public Number getVoteAverage() {
            return voteAverage;
        }

        public Result setVoteAverage(Number voteAverage) {
            this.voteAverage = voteAverage;
            return this;
        }

        public Integer getVoteCount() {
            return voteCount;
        }

        public Result setVoteCount(Integer voteCount) {
            this.voteCount = voteCount;
            return this;
        }
    }

}



