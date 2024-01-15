package com.coursemanagement.src.controllers;

import com.coursemanagement.src.bindings.CheckPermission;
import com.coursemanagement.src.data.Permissions;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/courses")
@RequestScoped
public class CoursesController {

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @CheckPermission(permission = Permissions.COURSE_CREATE)
    public Response createCourse(@Context SecurityContext ctx) {
        String name = ctx.getUserPrincipal().getName();
        System.out.println(name);
        return Response.accepted().build();
    }

}
