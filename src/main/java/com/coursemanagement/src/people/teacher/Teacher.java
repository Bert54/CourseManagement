package com.coursemanagement.src.people.teacher;

import com.coursemanagement.src.people.Person;

import java.util.List;

public class Teacher extends Person {

    public Teacher(String name) {
        super(name);
    }

    @Override
    public List<String> getPermissions() {
        return null;
    }
}
