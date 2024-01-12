package com.coursemanagement.src.manager.PeopleManager;

import com.coursemanagement.src.people.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PeopleManagerList implements PeopleManager {

    private final List<Person> registeredPeople;
    protected PeopleManagerList() {
        this.registeredPeople = new ArrayList<>();
    }

    public void addPerson(Person person) throws Exception {
        this.registeredPeople.add(person);
    }

    public Person getPerson(String name) throws IllegalStateException {
        AtomicReference<Person> fetchedPersonAtomic = new AtomicReference<>();

        this.registeredPeople.forEach((currentPerson) -> {
            if (currentPerson.getName().equals(name) && fetchedPersonAtomic.get() == null) {
                fetchedPersonAtomic.set(currentPerson);
            }
        });

        Person fetchedPerson = fetchedPersonAtomic.get();
        if (fetchedPerson == null) {
            throw new IllegalStateException("User with name '%s' was not found".formatted(name));
        }
        return fetchedPerson;
    }

}
