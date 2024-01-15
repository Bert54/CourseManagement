package com.coursemanagement.src.controllers;

import com.coursemanagement.src.controllers.responsebuilders.ResponseError;
import com.coursemanagement.src.controllers.responsebuilders.ResponseOK;
import com.coursemanagement.src.dto.people.AddPersonDto;
import com.coursemanagement.src.services.people.PeopleService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

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
            return ResponseOK.buildResponse(
                    Response.Status.CREATED,
                    this.peopleService.addPerson(addPersonDto)
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
            return ResponseOK.buildResponse(
                    Response.Status.OK,
                    this.peopleService.getPersonById(id)
            );
        } catch(Exception e) {
            return ResponseError.buildResponse(Response.Status.NOT_FOUND, e);
        }
    }

    @GET
    @Path("/name/{name}")
    public Response getPersonByName(@PathParam("name") String name) {
        try {
            return ResponseOK.buildResponse(
                    Response.Status.OK,
                    this.peopleService.getPersonByName(name.toLowerCase()));
        } catch (Exception e) {
            return ResponseError.buildResponse(Response.Status.NOT_FOUND, e);
        }
    }
}
