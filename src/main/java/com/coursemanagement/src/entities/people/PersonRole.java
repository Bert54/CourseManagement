package com.coursemanagement.src.entities.people;

import com.coursemanagement.src.dto.people.AddPersonDto;
import com.coursemanagement.src.entities.people.administrator.Administrator;
import com.coursemanagement.src.entities.people.student.Student;
import com.coursemanagement.src.entities.people.teacher.Teacher;

public class PersonRole {

    public static Person newPersonByRole(AddPersonDto addPersonDto) throws IllegalArgumentException {
        String personName = addPersonDto.name();

        try {
            PersonRoleEnum role = PersonRoleEnum.valueOf(addPersonDto.role());

            return switch (role) {
                case STUDENT -> new Student(personName);
                case TEACHER -> new Teacher(personName);
                case ADMINISTRATOR -> new Administrator(personName);
            };
        } catch ( IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Role '%s' is unknown", addPersonDto.role()));
        }
    }

}
