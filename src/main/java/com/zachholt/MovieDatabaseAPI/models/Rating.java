package com.zachholt.MovieDatabaseAPI.models;

import jakarta.persistence.*;

@Entity(name = "Ratings")
public class Rating {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "rating", nullable = false)
    private String rating;

    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public Rating setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getRating() {
        return rating;
    }

    public Rating setRating(String rating) {
        this.rating = rating;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Rating setDescription(String description) {
        this.description = description;
        return this;
    }
} 