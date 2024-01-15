package com.coursemanagement.src.entities.courses;

import com.coursemanagement.src.entities.people.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
public class Course implements Serializable {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch=FetchType.LAZY)
    private Person teacher;

    @Column(nullable = false)
    @Size(min = 1, max = 30)
    private String studentClass;

    @Column(nullable = false)
    @Size(min = 1, max = 200)
    private String title;

    @Column(nullable = false, length = 10000000)
    @Size(min = 1)
    private String content;

    public Course(Person person, String studentClass, String title, String content) {
        this.teacher = person;
        this.studentClass = studentClass;
        this.title = title;
        this.content = content;
    }

    public Course() {}

    public int getId() {
        return this.id;
    }

    public Person getTeacher() {
        return this.teacher;
    }

    public String getStudentClass() {
        return this.studentClass;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

}
