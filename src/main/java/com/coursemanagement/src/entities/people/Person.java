package com.coursemanagement.src.entities.people;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public abstract class Person {

    public static final int NOID = -1;

    @Column
    @Id
    @GeneratedValue
    private int id = NOID;

    @Column
    private String name;

//    @Column
//    @Enumerated(EnumType.STRING)
//    protected PersonRoleEnum role;

    protected Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    public void setId(int id) {
        if (this.id == NOID && id >= 0) {
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
