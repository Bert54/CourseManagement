package com.coursemanagement.src.controllers;

import com.coursemanagement.src.manager.PeopleManager.PeopleManager;
import com.coursemanagement.src.people.Person;
import com.coursemanagement.src.people.student.Student;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/people")
public class People {

    @Inject
    private PeopleManager peopleManager;


    @GET
    @Path("/add/{name}")
    @Produces("text/plain")
    public String add(@PathParam("name") String name) {
        try {
            this.peopleManager.addPerson(new Student(name));
            return "OK";
        } catch (Exception e) {
            return "FAIL";
        }
    }
}
