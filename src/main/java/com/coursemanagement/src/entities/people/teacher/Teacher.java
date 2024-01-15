package com.coursemanagement.src.entities.people.teacher;

import com.coursemanagement.src.data.Permissions;
import com.coursemanagement.src.entities.people.Person;
import com.coursemanagement.src.entities.people.PersonRoleEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.util.Arrays;
import java.util.List;

@Entity
@DiscriminatorValue("2")
public class Teacher extends Person {

    public Teacher(String name) {
        super(name);
    }

    public Teacher(int id) {
        super(id);
    }

    public Teacher() {
        super();
    }

    @Override
    @Transient
    public List<String> getPermissions() {
        return List.of(
                Permissions.COURSE_CREATE,
                Permissions.COURSE_FETCH
        );
    }
}
