package com.example;
import com.example.service.ActorService;
import com.models.Actor;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.validation.Valid;
import java.util.List;

import static io.micronaut.http.MediaType.APPLICATION_JSON;

@Controller("/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @Get()
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actors list", operationId = "actor_list", description = "desc actor list")
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = APPLICATION_JSON,
                    schema = @Schema(implementation = Actor.class, type = "array")
            )
    )
    public List<Actor> getActors() {
        return actorService.getActors();
    }

    @Get("/{id}")
    public HttpResponse<Actor> getActor(Long id) {
        return actorService.getActor(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }



    @Post
    public HttpResponse<Actor> addActor(@Body @Valid Actor actor) {
        actorService.addActor(actor);
        return HttpResponse.created(actor);
    }


    @Delete("/{id}")
    public HttpResponse deleteActor(int id) {
        if (actorService.deleteActor(id)) {
            return HttpResponse.noContent();
        } else {
            return HttpResponse.notFound();
        }
    }
}
