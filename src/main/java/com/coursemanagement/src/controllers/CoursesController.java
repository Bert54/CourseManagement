package com.coursemanagement.src.controllers;

import com.coursemanagement.src.bindings.CheckPermission;
import com.coursemanagement.src.controllers.responsebuilders.ResponseError;
import com.coursemanagement.src.controllers.responsebuilders.ResponseOK;
import com.coursemanagement.src.data.Permissions;
import com.coursemanagement.src.dto.courses.AddCourseDto;
import com.coursemanagement.src.services.courses.CoursesServiceBase;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
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
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class CoursesController {

    @Inject
    private CoursesServiceBase courseService;

    @POST
    @CheckPermission(permission = Permissions.COURSE_CREATE)
    public Response createCourse(@Context SecurityContext ctx, AddCourseDto addCourseDto) {
        try {
            return ResponseOK.buildResponse(
                    Response.Status.CREATED,
                    this.courseService.addCourse(
                            addCourseDto,
                            ctx.getUserPrincipal().getName()));
        } catch (Exception e) {
            return ResponseError.buildResponse(Response.Status.INTERNAL_SERVER_ERROR, e);
        }
    }

}
