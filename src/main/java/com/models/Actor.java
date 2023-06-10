package com.models;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Introspected
@Serdeable.Serializable
@Serdeable.Deserializable
public class Actor {

    private static int idCounter = 0;

    private int id;
    private String firstName;
    private String lastName;
    private int rating;
    private boolean oscarPrized;

    public Actor(String firstName, String lastName, int rating, boolean oscarPrized) {
        this.id = ++idCounter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
        this.oscarPrized = oscarPrized;
    }
}

