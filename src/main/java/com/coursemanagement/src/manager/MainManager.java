package com.coursemanagement.src.manager;

import com.coursemanagement.src.people.Person;
import com.coursemanagement.src.manager.PeopleManager.PeopleManager;

public class MainManager {

    private final PeopleManager peopleManager;

    public MainManager(PeopleManager peopleManager) {
        this.peopleManager = peopleManager;
    }

    public void addPerson(Person person) throws Exception {
        this.peopleManager.addPerson(person);
    }

    public Person getPerson(String name) throws Exception {
        return this.peopleManager.getPerson(name);
    }

}
