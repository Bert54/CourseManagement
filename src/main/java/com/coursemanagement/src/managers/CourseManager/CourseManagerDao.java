package com.coursemanagement.src.managers.CourseManager;

import com.coursemanagement.src.configuration.EntityManagerProvider;
import com.coursemanagement.src.entities.courses.Course;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

@Singleton
@Default
@Transactional
public class CourseManagerDao implements CourseManager {

    @Inject
    private EntityManagerProvider entityManager;

    @Override
    public Course addCourse(Course course) {
        try {
            this.entityManager.getEntityManager().persist(course);
            return course;
        } catch (Exception e) {
            throw new IllegalArgumentException("Something went wrong when saving the course");
        }

    }
}
