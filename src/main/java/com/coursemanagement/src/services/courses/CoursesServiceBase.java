package com.coursemanagement.src.services.courses;

import com.coursemanagement.src.dto.courses.AddCourseDto;
import com.coursemanagement.src.entities.courses.Course;

public interface CoursesServiceBase {

    Course addCourse(AddCourseDto addCourseDto, String teacherName) throws Exception;

}
