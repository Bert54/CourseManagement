package com.coursemanagement.dto;


import com.coursemanagement.src.dto.people.AddPersonDto;
import com.coursemanagement.src.entities.people.Person;
import com.coursemanagement.src.entities.people.administrator.Administrator;
import com.coursemanagement.src.entities.people.student.Student;
import com.coursemanagement.src.entities.people.teacher.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class AddPersonDtoTest {

    @Test
    @DisplayName("Test method AddPersonDto.format()")
    void testFormat() {
        // Unchanged
        AddPersonDto dto = new AddPersonDto("james", "STUDENT");
        assertThat(dto.format(), is(new AddPersonDto("james", "STUDENT")));

        // Uppercase
        dto = new AddPersonDto("james", "STUDenT");
        assertThat(dto.format(), is(new AddPersonDto("james", "STUDENT")));

        dto = new AddPersonDto("james", "student");
        assertThat(dto.format(), is(new AddPersonDto("james", "STUDENT")));

        // Trim
        dto = new AddPersonDto(" james", "  student   ");
        assertThat(dto.format(), is(new AddPersonDto("james", "STUDENT")));

        // Trim + Uppercase

        dto = new AddPersonDto(" james   ", "  ADMINISTRATOR");
        assertThat(dto.format(), is(new AddPersonDto("james", "ADMINISTRATOR")));
    }

    @Test
    @DisplayName("Test method AddPersonDto.toPersonEntity() - OK")
    void testToPersonEntityOK() {
        AddPersonDto dto = new AddPersonDto("natalya", "STUDENT");
        Person gottenPerson = dto.toPersonEntity();
        assertThat(
                dto.toPersonEntity(),
                instanceOf(Student.class)
        );
        assertEquals(gottenPerson.getName(), "natalya");
        assertEquals(gottenPerson.getId(), 0);

        dto = new AddPersonDto("boris", "TEACHER");
        gottenPerson = dto.toPersonEntity();
        assertThat(
                dto.toPersonEntity(),
                instanceOf(Teacher.class)
        );
        assertEquals(gottenPerson.getName(), "boris");
        assertEquals(gottenPerson.getId(), 0);

        dto = new AddPersonDto("james", "ADMINISTRATOR");
        gottenPerson = dto.toPersonEntity();
        assertThat(
                dto.toPersonEntity(),
                instanceOf(Administrator.class)
        );
        assertEquals(gottenPerson.getName(), "james");
        assertEquals(gottenPerson.getId(), 0);
    }

    @Test
    @DisplayName("Test method AddPersonDtoTest.toPersonEntity() - Unknown role")
    void testToPersonEntityBadRole() {
        // Empty
        AddPersonDto dto = new AddPersonDto("natalya", "");
        assertThrows(IllegalArgumentException.class, dto::toPersonEntity);

        // Bad role
        dto = new AddPersonDto("natalya", "GOD");
        assertThrows(IllegalArgumentException.class, dto::toPersonEntity);

        dto = new AddPersonDto("natalya", "gqijbngilm");
        assertThrows(IllegalArgumentException.class, dto::toPersonEntity);
    }

}
