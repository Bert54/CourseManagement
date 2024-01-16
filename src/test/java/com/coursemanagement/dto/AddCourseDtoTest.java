package com.coursemanagement.dto;

import com.coursemanagement.src.dto.courses.AddCourseDto;
import com.coursemanagement.src.dto.people.AddPersonDto;
import com.coursemanagement.src.entities.courses.Course;
import com.coursemanagement.src.entities.people.Person;
import com.coursemanagement.src.entities.people.administrator.Administrator;
import com.coursemanagement.src.entities.people.student.Student;
import com.coursemanagement.src.entities.people.teacher.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.withSettings;

public class AddCourseDtoTest {

    @Test
    @DisplayName("Test method AddCourseDto.format()")
    void testFormat() {
        // Unchanged
        AddCourseDto dto = new AddCourseDto("janus", "Severnaya base", "...");
        assertThat(dto.format(), is(new AddCourseDto("janus", "Severnaya base", "...")));

        // Trim

        dto = new AddCourseDto(" janus   ", " Severnaya base   ", " ...  ");
        assertThat(dto.format(), is(new AddCourseDto("janus", "Severnaya base", "...")));

        // Capitalize
        dto = new AddCourseDto("janus", "severnaya base", "...");
        assertThat(dto.format(), is(new AddCourseDto("janus", "Severnaya base", "...")));

        // Trim + Capitalize
        dto = new AddCourseDto(" janus   ", " severnaya base   ", " ...  ");
        assertThat(dto.format(), is(new AddCourseDto("janus", "Severnaya base", "...")));
    }

    @Test
    @DisplayName("Test method AddCourseDto.toPersonEntity() - OK")
    @ExtendWith(MockitoExtension.class)
    void testToPersonEntityOK() {
        AddCourseDto dto = new AddCourseDto("janus", "Severnaya base", "...");
        Person personMock = Mockito.mock(Person.class, withSettings().serializable());
        Course gottenCourse = dto.toCourseEntity(personMock);
        assertEquals(gottenCourse.getTeacher(), personMock);
        assertEquals(gottenCourse.getStudentClass(), "janus");
        assertEquals(gottenCourse.getTitle(), "Severnaya base");
        assertEquals(gottenCourse.getContent(), "...");
    }

    @Test
    @DisplayName("Test method AddCourseDto.toPersonEntity() - Empty person")
    void testToPersonEntityEmptyPerson() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    AddCourseDto dto = new AddCourseDto("janus", "Severnaya base", "...");
                    dto.toCourseEntity(null);
                });
    }

}
