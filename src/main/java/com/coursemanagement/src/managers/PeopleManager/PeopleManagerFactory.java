package com.coursemanagement.src.managers.PeopleManager;

public abstract class PeopleManagerFactory {

    public PeopleManager create() {
        return bootstrap();
    }

    protected abstract PeopleManager bootstrap();
}
