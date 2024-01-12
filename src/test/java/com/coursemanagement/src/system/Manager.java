package com.coursemanagement.src.system;

import com.coursemanagement.src.people.Person;

import java.util.ArrayList;
import java.util.Collection;

public class Manager {

    // TODO this could be easily managed with a database. Replace this with an interface whose purpose would be to
    // TODO manage registered people so that our implementation wih Collection<Person> can easily be swapped
    private final Collection<Person> registeredPeople;

    public Manager() {
        this.registeredPeople = new ArrayList<>();
    }

    // TODO add exception so that if the person whose name is already present, then we throw one
    public void AddPerson(Person person) {
        this.registeredPeople.add(person);
    }

}
