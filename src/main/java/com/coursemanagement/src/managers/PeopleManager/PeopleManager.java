package com.coursemanagement.src.managers.PeopleManager;

import com.coursemanagement.src.entities.people.Person;

public interface PeopleManager {

    Person addPerson(Person person) throws Exception;

    Person getPersonByName(String name) throws Exception;

    Person getPersonById(int id) throws Exception;

}
