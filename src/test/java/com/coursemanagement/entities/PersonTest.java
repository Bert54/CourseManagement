package com.coursemanagement.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.coursemanagement.src.entities.people.Person;
import com.coursemanagement.src.entities.people.student.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonTest {

    private static final String DEFAULT_NAME = "james";

    private Person person;

    @BeforeEach
    void init() {
        this.person = new Student(DEFAULT_NAME);
    }

    @Test
    @DisplayName("Test Person.getId()")
    void testId() {

        // Test default value
        assertEquals(0, this.person.getId());

        this.person.setId(0);
        assertEquals(0, this.person.getId());

        this.person.setId(1);
        assertEquals(1, this.person.getId());
    }

}
