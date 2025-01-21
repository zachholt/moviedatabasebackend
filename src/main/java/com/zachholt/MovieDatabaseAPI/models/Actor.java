package com.zachholt.MovieDatabaseAPI.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity(name = "Actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "dateOfBirth", nullable = false)
    private String dateOfBirth;

    public Integer getId() {
        return id;
    }

    public Actor setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Actor setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Actor setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Actor setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }
}
