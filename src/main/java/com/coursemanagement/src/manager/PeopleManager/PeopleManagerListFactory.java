package com.coursemanagement.src.manager.PeopleManager;

public class PeopleManagerListFactory extends PeopleManagerFactory {

    private static final PeopleManagerFactory instance = new PeopleManagerListFactory();

    public static PeopleManagerFactory getInstance() {
        return instance;
    }

    @Override
    protected PeopleManager bootstrap() {
        return new PeopleManagerList();
    }
}
