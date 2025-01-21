package com.zachholt.MovieDatabaseAPI.models;

import jakarta.persistence.*;

@Entity(name = "Genres")
public class Genre {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public Genre setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public Genre setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Genre setDescription(String description) {
        this.description = description;
        return this;
    }
}
