package com.coursemanagement.src.entities.people.student;

import com.coursemanagement.src.data.Permissions;
import com.coursemanagement.src.entities.people.Person;
import com.coursemanagement.src.entities.people.PersonRoleEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.util.List;
import java.util.Locale;

@Entity
@DiscriminatorValue("3")
public class Student extends Person {

    public Student(String name) {
        super(name);
    }

    public Student() {

    }

    @Override
    @Transient
    public List<String> getPermissions() {
        return List.of(
                Permissions.COURSE_STUDENT_FETCH
        );
    }

}
