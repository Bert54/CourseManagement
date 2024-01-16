package com.coursemanagement.src.services.courses;

import com.coursemanagement.src.dto.courses.AddCourseDto;
import com.coursemanagement.src.entities.courses.Course;

import java.util.List;

public interface CoursesServiceBase {

    Course addCourse(AddCourseDto addCourseDto, String userName) throws Exception;

    List<Course> getCoursesByUserName(String userName) throws Exception;

}
