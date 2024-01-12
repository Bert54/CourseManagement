package com.coursemanagement.src.people.teacher;

import com.coursemanagement.src.people.Person;

import java.util.List;

public class teacher extends Person {

    protected teacher(String name) {
        super(name);
    }

    @Override
    public List<String> getPermissions() {
        return null;
    }
}
