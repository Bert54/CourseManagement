package com.coursemanagement.src.managers.CourseManager;

import com.coursemanagement.src.entities.courses.Course;

import java.util.List;

public interface CourseManager {

    Course addCourse(Course course);

    List<Course> getAllCoursesByUserId(int userId);

}
