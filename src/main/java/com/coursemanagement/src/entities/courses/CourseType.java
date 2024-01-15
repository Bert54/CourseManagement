package com.coursemanagement.src.entities.courses;

import com.coursemanagement.src.dto.courses.AddCourseDto;
import com.coursemanagement.src.entities.people.Person;
import com.coursemanagement.src.entities.people.teacher.Teacher;

public class CourseType {

    public static Course newCourse(AddCourseDto addCourseDto, Person person) {
        return new Course(
                person,
                addCourseDto.studentClass(),
                addCourseDto.title(),
                addCourseDto.content());
    }

}
