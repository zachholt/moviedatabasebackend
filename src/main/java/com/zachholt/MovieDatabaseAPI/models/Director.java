package com.zachholt.MovieDatabaseAPI.models;

import jakarta.persistence.*;

@Entity(name = "Directors")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "firstName", nullable = false, length = 100)
    private String firstName;
    @Column(name = "lastName", nullable = false, length = 100)
    private String lastName;
    @Column(name = "dateOfBirth", nullable = false)
    private String dateOfBirth;

    public Integer getId() {
        return id;
    }

    public Director setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Director setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Director setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Director setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }
}
