package com.coursemanagement.src.services.people;

import com.coursemanagement.src.controllers.responsebuilders.ResponseOK;
import com.coursemanagement.src.dto.people.AddPersonDto;
import com.coursemanagement.src.entities.people.Person;
import com.coursemanagement.src.managers.PeopleManager.PeopleManager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class PeopleService implements PeopleServiceBase{

    @Inject
    private PeopleManager peopleManager;

    @Override
    public Person addPerson(AddPersonDto addPersonDto) throws Exception {
        return this.peopleManager.addPerson(
                addPersonDto.format().toPersonEntity()
        );
    }

    @Override
    public Person getPersonById(int id) throws Exception {
        return this.peopleManager.getPersonById(id);
    }

    @Override
    public Person getPersonByName(String name) throws Exception {
        return this.peopleManager.getPersonByName(name.toLowerCase());
    }

}
