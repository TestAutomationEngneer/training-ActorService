package com.example.repository;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Actor;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class ActorRepository {

    private List<Actor> actors = new ArrayList<>(); // Wczytaj dane z pliku JSON do tej listy przy starcie aplikacji


    @EventListener
    public void onStartup(StartupEvent event) {
//        // Wczytywanie aktorów z pliku JSON przy starcie serwisu
        ObjectMapper mapper = new ObjectMapper();

        try {
            File file = new File(
                    getClass().getClassLoader().getResource("actors.json").getFile()
            );
            actors = mapper.readValue(file, new TypeReference<List<Actor>>(){});

            // Przypisz unikalne ID dla każdego aktora
            for (int i = 0; i < actors.size(); i++) {
                actors.get(i).setId(i + 1);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading actors: " + e.getMessage());
        }
    }


    public List<Actor> findAll() {
        return actors;
    }

    public Optional<Actor> findById(int id) {
        return actors.stream().filter(actor -> actor.getId() == id).findFirst();
    }

    public void save(Actor actor) {
        actors.add(actor);
    }

    public boolean deleteById(int id) {
        return actors.removeIf(actor -> actor.getId() == id);
    }

//    public boolean update(Actor actor) {
//        Optional<Actor> existingActor = findById(actor.getId());
//        existingActor.ifPresent(eActor -> {
//            eActor.setFirstName(actor.getFirstName());
//            eActor.setLastName(actor.getLastName());
//            eActor.setRating(actor.getRating());
//            eActor.setOscarPrized(actor.isOscarPrized());
//        });
//        return existingActor.isPresent();
//    }
}
