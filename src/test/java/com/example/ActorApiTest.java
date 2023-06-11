package com.example;

import com.example.repository.ActorRepository;
import com.models.Actor;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class ActorApiTest {

    @Inject
    ActorRepository actorRepository;

    @Test
    void shouldGetCorrectActor() {
        Actor expectedActor = new Actor("Brad", "Pitt", 2, true);
        Actor actualActor = new Actor("Brad", "Pitt", 2, true);

        assertThat(expectedActor.getFirstName()).isEqualTo(actualActor.getFirstName());
        assertThat(expectedActor.getLastName()).isEqualTo(actualActor.getLastName());
        assertThat(expectedActor.getRating()).isEqualTo(actualActor.getRating());
    }

    @Test
    void shouldGetAllActors() {
        Actor brad = new Actor("Brad", "Pitt", 2, true);
        Actor angelina = new Actor("Angelina", "Jolie", 12, true);

        List<Actor> actors = List.of(brad, angelina);
        assertThat(actors).hasSize(2);
    }

    @Test
    void shouldLoadActorsRepository() {
      var loadedActors = actorRepository.getActors();
        assertThat(loadedActors).hasSize(20);
    }
}
