package com.coursemanagement.entities;

import static com.coursemanagement.src.entities.people.Person.NOID;
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
        assertEquals(NOID, this.person.getId());

        // Can be set
        this.person.setId(0);
        assertEquals(0, this.person.getId());

        // Can NOT be set
        this.person.setId(1);
        assertEquals(0, this.person.getId());
    }

}