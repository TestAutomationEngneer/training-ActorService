package com.example.service;

import com.example.repository.ActorRepository;
import com.models.Actor;
import jakarta.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getActors() {
        return actorRepository.findAll();
    }

    public Optional<Actor> getActor(Long id) {
        return actorRepository.findById(id);
    }

    public void addActor(Actor actor) {
        actorRepository.save(actor);
    }

    public boolean deleteActor(Long id) {
        return actorRepository.deleteById(id);
    }
}
