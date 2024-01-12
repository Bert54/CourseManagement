package com.coursemanagement.src.manager.PeopleManager;

import com.coursemanagement.src.people.Person;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
public class PeopleManagerList implements PeopleManager {

    private final List<Person> registeredPeople;
    protected PeopleManagerList() {
        this.registeredPeople = new ArrayList<>();
    }

    public void addPerson(Person person) throws Exception {
        AtomicBoolean exists = new AtomicBoolean(false);

        this.registeredPeople.forEach((currentPerson) -> {
            if (currentPerson.getName().equals(person.getName())) {
                exists.set(true);
            }
        });

        if (exists.get()) {
            throw new IllegalStateException(String.format("User with name '%s' already exists", person.getName()));
        }

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
            throw new IllegalStateException(String.format("User with name '%s' was not found", name));
        }
        return fetchedPerson;
    }

}
