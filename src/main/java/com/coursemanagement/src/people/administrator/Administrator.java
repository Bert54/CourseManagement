package com.coursemanagement.src.people.administrator;

import com.coursemanagement.src.people.Person;

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
