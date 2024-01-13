package com.coursemanagement.src.entities.people.administrator;

import com.coursemanagement.src.entities.people.Person;

import java.util.List;

public class Administrator extends Person {

    public Administrator(String name) {
        super(name);
    }

    @Override
    public List<String> getPermissions() {
        return null;
    }

}
