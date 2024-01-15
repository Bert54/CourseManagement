package com.coursemanagement.src.managers.PeopleManager;

import com.coursemanagement.src.configuration.EntityManagerProvider;
import com.coursemanagement.src.entities.people.Person;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@Singleton
@Default
@Transactional
public class PeopleManagerDao implements PeopleManager {

    @Inject
    private EntityManagerProvider entityManager;

    @Override
    public Person addPerson(Person person) throws IllegalArgumentException {
        try {
            this.entityManager.getEntityManager().persist(person);
            return person;
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("User with name '%s' already exists", person.getName()));
        }

    }

    @Override
    public Person getPersonByName(String name) throws IllegalArgumentException {
        TypedQuery<Person> query = this.entityManager.getEntityManager().createQuery(
                "SELECT p FROM Person AS p WHERE p.name = :name", Person.class
        ).setParameter("name", name);

        List<Person> result = query.getResultList();
        if (result.isEmpty()) {
            throw new IllegalStateException(
                    String.format("User with name '%s' was not found", name)
            );
        }

        return result.get(0);
    }

    @Override
    public Person getPersonById(int id) throws IllegalArgumentException {
        TypedQuery<Person> query = this.entityManager.getEntityManager().createQuery(
                "SELECT p FROM Person AS p WHERE p.id = :id", Person.class
        ).setParameter("id", id);

        List<Person> result = query.getResultList();
        if (result.isEmpty()) {
            throw new IllegalStateException(
                    String.format("User with id '%d' was not found", id)
            );
        }

        return result.get(0);
    }

}
