package com.coursemanagement.src.controllers;

import com.coursemanagement.src.controllers.responsebuilders.ResponseError;
import com.coursemanagement.src.controllers.responsebuilders.ResponseOK;
import com.coursemanagement.src.dto.people.AddPersonDto;
import com.coursemanagement.src.managers.PeopleManager.PeopleManager;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/people")
@RequestScoped
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class PeopleController {

    @Inject
    private PeopleManager peopleManager;

    @POST
    public Response addPerson(AddPersonDto addPersonDto) throws Exception {
        try {
            return ResponseOK.buildResponse(
                    Response.Status.CREATED,
                    this.peopleManager.addPerson(
                            addPersonDto.format().toPersonEntity()
                    )
            );
        } catch (Exception e) {
            return ResponseError.buildResponse(Response.Status.BAD_REQUEST, e);
        }
    }

    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") String idStr) {
        try {
            int id = Integer.parseInt(idStr);

            return ResponseOK.buildResponse(
                    Response.Status.OK,
                    this.peopleManager.getPersonById(id)
            );
        } catch (NumberFormatException e) {
            return ResponseError.buildResponse(Response.Status.BAD_REQUEST,
                    new IllegalArgumentException(
                            String.format("'%s' is not a valid id", idStr))
            );
        } catch (Exception e) {
            return ResponseError.buildResponse(Response.Status.NOT_FOUND, e);
        }
    }

    @GET
    @Path("/name/{name}")
    public Response getPersonByName(@PathParam("name") String name) {
        try {
            return ResponseOK.buildResponse(
                    Response.Status.OK,
                    this.peopleManager.getPersonByName(name.toLowerCase()));
        } catch (Exception e) {
            return ResponseError.buildResponse(Response.Status.NOT_FOUND, e);
        }
    }
}
