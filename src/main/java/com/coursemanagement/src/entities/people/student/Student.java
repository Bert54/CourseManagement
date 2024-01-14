package com.coursemanagement.src.entities.people.student;

import com.coursemanagement.src.entities.people.Person;
import com.coursemanagement.src.entities.people.PersonRoleEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

import java.util.List;
import java.util.Locale;

@Entity
@DiscriminatorValue("STUDENT")
public class Student extends Person {

    public Student(String name) {
        super(name);
        //this.role = PersonRoleEnum.STUDENT;
    }

    public Student() {

    }

    @Override
    @Transient
    public List<String> getPermissions() {
        return List.of("tmp_student");
    }

}
