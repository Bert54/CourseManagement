package com.coursemanagement.src.controllers;

import com.coursemanagement.src.bindings.CheckPermission;
import com.coursemanagement.src.controllers.responsebuilders.ResponseError;
import com.coursemanagement.src.controllers.responsebuilders.ResponseOK;
import com.coursemanagement.src.data.Permissions;
import com.coursemanagement.src.dto.courses.AddCourseDto;
import com.coursemanagement.src.entities.people.Person;
import com.coursemanagement.src.filters.UserInfo;
import com.coursemanagement.src.managers.CourseManager.CourseManager;
import com.coursemanagement.src.managers.PeopleManager.PeopleManager;
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
    private CourseManager courseManager;

    @Inject
    private PeopleManager peopleManager;

    @POST
    @CheckPermission(permission = Permissions.COURSE_CREATE)
    public Response createCourse(@Context SecurityContext ctx, AddCourseDto addCourseDto) {
        Person person;
        try {
            person = this.peopleManager.getPersonByName(ctx.getUserPrincipal().getName());
        } catch (Exception e) {
            return ResponseError.buildResponse(Response.Status.NOT_FOUND, e);
        }

        try {
            return ResponseOK.buildResponse(
                    Response.Status.CREATED,
                    this.courseManager.addCourse(
                            addCourseDto.format().toCourseEntity(person)));
        } catch (Exception e) {
            return ResponseError.buildResponse(Response.Status.INTERNAL_SERVER_ERROR, e);
        }
    }

}
