package com.coursemanagement.src.manager.PeopleManager;

import com.coursemanagement.src.people.Person;

public interface PeopleManager {

    void addPerson(Person person) throws Exception;

    Person getPerson(String name)  throws Exception;

}
