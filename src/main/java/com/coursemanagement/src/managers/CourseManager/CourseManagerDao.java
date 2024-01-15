package com.coursemanagement.src.managers.CourseManager;

import com.coursemanagement.src.configuration.EntityManagerProvider;
import com.coursemanagement.src.entities.courses.Course;
import com.coursemanagement.src.entities.people.Person;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

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

    @Override
    public List<Course> getAllCoursesByUserId(int userId) {
        TypedQuery<Course> query = this.entityManager.getEntityManager().createQuery(
                "SELECT c FROM Course AS c WHERE c.teacher.id = :userId", Course.class
        ).setParameter("userId", userId);

        return query.getResultList();
    }


}
