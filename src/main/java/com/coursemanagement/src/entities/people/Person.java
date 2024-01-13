package com.coursemanagement.src.entities.people;

import java.util.List;



public abstract class Person {

    private static final int NOID = -1;

    private int id = NOID;
    private String name;

    protected Person(String name) {
        this.name = name;
    }

    public void setId(int id) {
        if (this.id == NOID && id >= 0) {
            this.id = id;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    abstract public List<String> getPermissions();

}
