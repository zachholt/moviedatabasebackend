package com.zachholt.MovieDatabaseAPI.models;

import jakarta.persistence.*;
import java.util.List;

@Entity(name = "Movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "externalId")
    private Integer externalId;

    @Column(name = "movieTitle", nullable = false)
    private String movieTitle;

    @Column(name = "movieLength")
    private Integer movieLength;

    @Column(name = "releaseDate")
    private String releaseDate;

    @Column(name = "trailerUrl")
    private String trailerUrl;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "director_id")
    private Director director;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "rating_id")
    private Rating rating;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "movie_actors",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors;

    @Column(name = "overview", columnDefinition = "TEXT")
    private String overview;

    public Integer getId() {
        return id;
    }

    public Movie setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getExternalId() {
        return externalId;
    }

    public Movie setExternalId(Integer externalId) {
        this.externalId = externalId;
        return this;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public Movie setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
        return this;
    }

    public Integer getMovieLength() {
        return movieLength;
    }

    public Movie setMovieLength(Integer movieLength) {
        this.movieLength = movieLength;
        return this;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Movie setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public Movie setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
        return this;
    }

    public Director getDirector() {
        return director;
    }

    public Movie setDirector(Director director) {
        this.director = director;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public Movie setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public Rating getRating() {
        return rating;
    }

    public Movie setRating(Rating rating) {
        this.rating = rating;
        return this;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public Movie setActors(List<Actor> actors) {
        this.actors = actors;
        return this;
    }

    public String getOverview() {
        return overview;
    }

    public Movie setOverview(String overview) {
        this.overview = overview;
        return this;
    }
}
