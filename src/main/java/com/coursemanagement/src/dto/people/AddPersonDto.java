package com.coursemanagement.src.dto.people;

import com.coursemanagement.src.entities.people.Person;
import com.coursemanagement.src.entities.people.PersonRole;

public record AddPersonDto(String name, String role) {

    public AddPersonDto format() {
        return new AddPersonDto(
                this.name.trim(),
                this.role.trim().toUpperCase()
        );
    }

    public Person toPersonEntity() throws IllegalArgumentException {
        return PersonRole.newPersonByRole(this);
    }

}

