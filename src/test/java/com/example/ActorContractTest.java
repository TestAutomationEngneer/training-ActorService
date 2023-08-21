package com.example;


import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.IgnoreNoPactsToVerify;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import com.example.service.ActorService;
import com.models.Actor;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@MicronautTest(transactional = false)
@Provider("actor")
//@PactBroker
@PactFolder("pacts")
@IgnoreNoPactsToVerify
@Tag("pact")
@Slf4j
public class ActorContractTest {

    @Inject
    private EmbeddedServer embeddedServer;

    @Inject
    private ActorService actorService;

    @BeforeEach
    void setupPact(PactVerificationContext context) {
        if (context != null) {
            context.setTarget(new HttpTestTarget("localhost", embeddedServer.getPort(), "/"));
        }
    }

    @State("Tomasz Karolak exists")
    public void tomaszKarolakExists()
    {
        Actor tomaszKarolak = new Actor(99L, "Tomasz", "Karolak", 99, false);
        actorService.addActor(tomaszKarolak);
        log.info(String.format("Contract Actor object created %s", tomaszKarolak));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationForActor(PactVerificationContext context) {
        if (context != null) {
            context.verifyInteraction();
        }
    }
}
