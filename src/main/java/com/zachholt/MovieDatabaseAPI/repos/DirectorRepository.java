package com.zachholt.MovieDatabaseAPI.repos;

import com.zachholt.MovieDatabaseAPI.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {
}
