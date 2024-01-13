package com.coursemanagement.src.controllers;

import com.coursemanagement.src.controllers.responsebuilders.ResponseError;
import com.coursemanagement.src.controllers.responsebuilders.ResponseOK;
import com.coursemanagement.src.dto.people.AddPersonDto;
import com.coursemanagement.src.managers.PeopleManager.PeopleManager;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/people")
public class PeopleController {

    @Inject
    private PeopleManager peopleManager;

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @RequestScoped
    public Response add(AddPersonDto addPersonDto) {
        try {
            return ResponseOK.buildResponse(
                    Response.Status.CREATED,
                    this.peopleManager.addPerson(
                            addPersonDto.toPersonEntity()
                    )
            );
        } catch (Exception e) {
            return ResponseError.buildResponse(
                    Response.Status.BAD_REQUEST,
                    e
            );
        }
    }
}
