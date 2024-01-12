package com.coursemanagement.src.people.student;

import com.coursemanagement.src.people.Person;

import java.util.List;

public class Student extends Person {

    public Student(String name) {
        super(name);
    }

    @Override
    public List<String> getPermissions() {
        return null;
    }
}
