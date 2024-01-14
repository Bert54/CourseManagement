package com.coursemanagement.src.managers.PeopleManager;

import com.coursemanagement.src.configuration.EntityManagerProvider;
import com.coursemanagement.src.entities.people.Person;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Singleton
@Default
@Transactional
public class PeopleManagerDao implements PeopleManager {

    @Inject
    private EntityManagerProvider entityManager;

    @Override
    public Person addPerson(Person person) throws Exception {
        try {
            this.entityManager.getEntityManager().persist(person);
            return person;
        } catch (Exception e) {
            throw new IllegalStateException(String.format("User with name '%s' already exists", person.getName()));
        }

    }

    @Override
    public Person getPersonByName(String name) throws Exception {
        return null;
    }

    @Override
    public Person getPersonById(int id) throws Exception {
        return null;
    }

}
