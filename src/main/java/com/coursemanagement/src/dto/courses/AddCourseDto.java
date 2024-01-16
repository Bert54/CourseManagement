package com.coursemanagement.src.dto.courses;

import com.coursemanagement.src.entities.courses.Course;
import com.coursemanagement.src.entities.courses.CourseType;
import com.coursemanagement.src.entities.people.Person;
import com.coursemanagement.src.entities.people.PersonRole;
import org.apache.commons.lang3.StringUtils;

public record AddCourseDto(String studentClass, String title, String content) {

    public AddCourseDto format() {
        return new AddCourseDto(
                this.studentClass.trim(),
                StringUtils.capitalize(this.title.trim()),
                this.content.trim()
        );
    }

    public Course toCourseEntity(Person person) throws IllegalArgumentException {
        if (person == null) {
            throw new IllegalArgumentException("Can't create a new Course with an empty Person");
        }
        return CourseType.newCourse(this, person);
    }

}
