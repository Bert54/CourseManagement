package com.coursemanagement.src.dto.people;

import com.coursemanagement.src.entities.people.Person;
import com.coursemanagement.src.entities.people.PersonRole;

public record AddPersonDto(String name, String role) {

    public Person toPersonEntity() throws Exception {
        return PersonRole.newPersonByRole(this);
    }

}

