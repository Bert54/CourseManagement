package com.coursemanagement.src.managers.PeopleManager;

import com.coursemanagement.src.entities.people.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
@Alternative
public class PeopleManagerList implements PeopleManager {

    private final List<Person> registeredPeople;
    protected PeopleManagerList() {
        this.registeredPeople = new ArrayList<>();
    }

    public Person addPerson(Person person) throws IllegalStateException {
        AtomicBoolean exists = new AtomicBoolean(false);

        this.registeredPeople.forEach((currentPerson) -> {
            if (currentPerson.getName().equals(person.getName())) {
                exists.set(true);
            }
        });

        if (exists.get()) {
            throw new IllegalArgumentException(String.format("User with name '%s' already exists", person.getName()));
        }

        person.setId(this.registeredPeople.size());
        this.registeredPeople.add(person);

        return person;
    }

    public Person getPersonByName(String name) throws IllegalStateException {
        AtomicReference<Person> fetchedPersonAtomic = new AtomicReference<>();

        this.registeredPeople.forEach((currentPerson) -> {
            if (currentPerson.getName().toLowerCase().equals(name)
                    && fetchedPersonAtomic.get() == null) {
                fetchedPersonAtomic.set(currentPerson);
            }
        });

        Person fetchedPerson = fetchedPersonAtomic.get();
        if (fetchedPerson == null) {
            throw new IllegalArgumentException(
                    String.format("User with name '%s' was not found", name)
            );
        }
        return fetchedPerson;
    }

    public Person getPersonById(int id) throws IllegalStateException {
        Person fetchedPerson;

        try {
            fetchedPerson = this.registeredPeople.get(id);
            if (fetchedPerson == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("User with id '%d' was not found", id));
        }

        return fetchedPerson;
    }

}
