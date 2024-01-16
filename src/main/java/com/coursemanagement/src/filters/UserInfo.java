package com.coursemanagement.src.filters;

import java.security.Principal;

public class UserInfo implements Principal {

    protected int id;

    protected String name;

    public UserInfo(int id, String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
