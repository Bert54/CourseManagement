package com.coursemanagement.src.people;

import java.util.List;

public abstract class Person {

    private String name;
    protected Person(String name) {
        this.name = name;
    }
    abstract public List<String> getPermissions();

}
