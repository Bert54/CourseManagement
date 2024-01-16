package com.coursemanagement.src.controllers;

import com.coursemanagement.src.bindings.CheckPermission;
import com.coursemanagement.src.controllers.responsebuilders.ResponseError;
import com.coursemanagement.src.controllers.responsebuilders.ResponseOK;
import com.coursemanagement.src.controllers.responsebuilders.ResponseOKOptional;
import com.coursemanagement.src.data.Permissions;
import com.coursemanagement.src.dto.courses.AddCourseDto;
import com.coursemanagement.src.entities.courses.Course;
import com.coursemanagement.src.services.courses.CoursesServiceBase;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObjectBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/courses")
@RequestScoped
@Produces(APPLICATION_JSON)
public class CoursesController {

    @Inject
    private CoursesServiceBase courseService;

    @POST
    @CheckPermission(permission = Permissions.COURSE_CREATE)
    @Consumes(APPLICATION_JSON)
    public Response createCourse(@Context SecurityContext ctx, AddCourseDto addCourseDto) {
        try {

            Course course = this.courseService.addCourse(
                    addCourseDto,
                    ctx.getUserPrincipal().getName());

            return ResponseOK.buildResponse(
                    Response.Status.CREATED,
                    this.buildJsonObjectCourse(course).build());
        } catch (Exception e) {
            return ResponseError.buildResponse(Response.Status.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GET
    @CheckPermission(permission = Permissions.COURSE_FETCH)
    public Response getCourses(@Context SecurityContext ctx) {
        try {
            List<Course> courses = this.courseService.getCoursesByUserName(
                    ctx.getUserPrincipal().getName());

            return ResponseOK.buildResponse(
                    Response.Status.OK,
                    buildJsonArrayCourseList(courses).build(),
                    (opt) -> opt.type(APPLICATION_JSON));
        } catch (Exception e) {
            return ResponseError.buildResponse(Response.Status.INTERNAL_SERVER_ERROR, e);
        }
    }

    private JsonArrayBuilder buildJsonArrayCourseList(List<Course> courses) throws Exception {
        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        JsonArrayBuilder coursesJson = factory.createArrayBuilder();
        for (Course course: courses) {
            coursesJson.add(this.buildJsonObjectCourse(course));
        }
        return coursesJson;
    }

    private JsonObjectBuilder buildJsonObjectCourse(Course course) throws Exception {
        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
            return factory.createObjectBuilder()
                    .add("id", course.getId())
                    .add("user_id", course.getTeacher().getId())
                    .add("student_class", course.getStudentClass())
                    .add("title", course.getTitle())
                    .add("content", course.getContent());
    }

}
