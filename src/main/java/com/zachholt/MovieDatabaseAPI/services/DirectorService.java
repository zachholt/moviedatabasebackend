package com.zachholt.MovieDatabaseAPI.services;

import com.zachholt.MovieDatabaseAPI.models.Director;
import com.zachholt.MovieDatabaseAPI.repos.DirectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {
    private final DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public Director findDirectorById(Integer id) {
        Optional<Director> director = directorRepository.findById(id);
        return director.orElse(null);
    }

    public List<Director> findAllDirectors() {
        return directorRepository.findAll();
    }

    public Director createDirector(Director director) {
        return directorRepository.save(director);
    }

    public Director updateDirector(Director director) {
        if (directorRepository.existsById(director.getId())) {
            return directorRepository.save(director);
        }
        return null;
    }

    public boolean deleteDirector(Integer id) {
        if (directorRepository.existsById(id)) {
            directorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
