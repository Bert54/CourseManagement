package com.coursemanagement.src.managers.PeopleManager;

import com.coursemanagement.src.entities.people.Person;

public interface PeopleManager {

    Person addPerson(Person person) throws Exception;

    Person getPerson(String name)  throws Exception;

}
