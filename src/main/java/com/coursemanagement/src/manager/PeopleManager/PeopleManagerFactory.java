package com.coursemanagement.src.manager.PeopleManager;

public abstract class PeopleManagerFactory {

    public PeopleManager create() {
        return bootstrap();
    }

    protected abstract PeopleManager bootstrap();
}
