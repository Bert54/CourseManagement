package com.coursemanagement.src.entities.people;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Person implements Serializable {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, max = 100)
    @Column(unique = true)
    private String name;

    protected Person(String name) {
        this.name = name;
    }

    protected Person(int id) {
        this.id = id;
    }

    public Person() {
    }

    public void setId(int id) {
        if (id >= 0) {
            this.id = id;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    abstract public List<String> getPermissions();

}
