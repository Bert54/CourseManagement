package com.coursemanagement.src.manager.PeopleManager;

import com.coursemanagement.src.entities.people.Person;

public interface PeopleManager {

    Person addPerson(Person person) throws Exception;

    Person getPerson(String name)  throws Exception;

}
