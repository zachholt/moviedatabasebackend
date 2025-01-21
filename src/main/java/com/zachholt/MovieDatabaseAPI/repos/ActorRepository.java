package com.zachholt.MovieDatabaseAPI.repos;

import com.zachholt.MovieDatabaseAPI.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
} 