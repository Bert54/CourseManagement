package com.coursemanagement.src.services.courses;

import com.coursemanagement.src.dto.courses.AddCourseDto;
import com.coursemanagement.src.entities.courses.Course;
import com.coursemanagement.src.entities.people.Person;
import com.coursemanagement.src.managers.CourseManager.CourseManager;
import com.coursemanagement.src.managers.PeopleManager.PeopleManager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CoursesService implements CoursesServiceBase {

    @Inject
    private CourseManager courseManager;

    @Inject
    private PeopleManager peopleManager;

    @Override
    public Course addCourse(AddCourseDto addCourseDto, String userName) throws Exception {

        Person person = this.peopleManager.getPersonByName(userName);
        return this.courseManager.addCourse(
                addCourseDto.format().toCourseEntity(person));

    }

    @Override
    public List<Course> getCoursesByUserName(String userName) throws Exception {
        Person person = this.peopleManager.getPersonByName(userName);
        return this.courseManager.getAllCoursesByUserId(person.getId());
    }

}
