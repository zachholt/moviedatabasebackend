package com.zachholt.MovieDatabaseAPI.services;

import com.zachholt.MovieDatabaseAPI.models.Actor;
import com.zachholt.MovieDatabaseAPI.repos.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Actor findActorById(Integer id) {
        Optional<Actor> actor = actorRepository.findById(id);
        return actor.orElse(null);
    }

    public List<Actor> findAllActors() {
        return actorRepository.findAll();
    }

    public Actor createActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public Actor updateActor(Actor actor) {
        if (actorRepository.existsById(actor.getId())) {
            return actorRepository.save(actor);
        }
        return null;
    }

    public boolean deleteActor(Integer id) {
        if (actorRepository.existsById(id)) {
            actorRepository.deleteById(id);
            return true;
        }
        return false;
    }
} 