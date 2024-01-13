package com.coursemanagement.src.managers;

import com.coursemanagement.src.entities.people.Person;
import com.coursemanagement.src.managers.PeopleManager.PeopleManager;

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
