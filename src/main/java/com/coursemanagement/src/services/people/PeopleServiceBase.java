package com.coursemanagement.src.services.people;

import com.coursemanagement.src.dto.people.AddPersonDto;
import com.coursemanagement.src.entities.people.Person;

public interface PeopleServiceBase {

    Person addPerson(AddPersonDto addPersonDto) throws Exception;

    Person getPersonById(int id) throws Exception;

    Person getPersonByName(String name) throws Exception;

}
