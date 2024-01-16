package com.coursemanagement.src.controllers;

import com.coursemanagement.src.controllers.responsebuilders.ResponseError;
import com.coursemanagement.src.controllers.responsebuilders.ResponseOK;
import com.coursemanagement.src.dto.people.AddPersonDto;
import com.coursemanagement.src.entities.courses.Course;
import com.coursemanagement.src.entities.people.Person;
import com.coursemanagement.src.services.people.PeopleService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObjectBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/people")
@RequestScoped
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class PeopleController {

    @Inject
    private PeopleService peopleService;

    @POST
    public Response addPerson(AddPersonDto addPersonDto) {
        try {
            Person person = this.peopleService.addPerson(addPersonDto);

            return ResponseOK.buildResponse(
                    Response.Status.CREATED,
                    buildJsonObjectPerson(person).build()
            );
        } catch (Exception e) {
            return ResponseError.buildResponse(Response.Status.BAD_REQUEST, e);
        }
    }

    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") String idStr) {
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            return ResponseError.buildResponse(Response.Status.BAD_REQUEST,
                    new IllegalArgumentException(
                            String.format("'%s' is not a valid id", idStr))
            );
        }

        try {
            Person person = this.peopleService.getPersonById(id);

            return ResponseOK.buildResponse(
                    Response.Status.OK,
                    buildJsonObjectPerson(person).build()
            );
        } catch(Exception e) {
            return ResponseError.buildResponse(Response.Status.NOT_FOUND, e);
        }
    }

    @GET
    @Path("/name/{name}")
    public Response getPersonByName(@PathParam("name") String name) {
        try {

            Person person = this.peopleService.getPersonByName(name.toLowerCase());

            return ResponseOK.buildResponse(
                    Response.Status.OK,
                    buildJsonObjectPerson(person).build()
            );
        } catch (Exception e) {
            return ResponseError.buildResponse(Response.Status.NOT_FOUND, e);
        }
    }

    private JsonObjectBuilder buildJsonObjectPerson(Person person) throws Exception {
        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);

        // Build permissions array
        JsonArrayBuilder coursesJson = factory.createArrayBuilder();
        for (String permission : person.getPermissions()) {
            coursesJson.add(permission);
        }

        return factory.createObjectBuilder()
                .add("id", person.getId())
                .add("name", person.getName())
                .add("permissions", coursesJson);
    }
}
