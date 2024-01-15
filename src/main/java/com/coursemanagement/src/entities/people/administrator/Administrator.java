package com.coursemanagement.src.entities.people.administrator;

import com.coursemanagement.src.entities.people.Person;
import com.coursemanagement.src.entities.people.PersonRoleEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.util.List;

@Entity
@DiscriminatorValue("1")
public class Administrator extends Person {

    public Administrator(String name) {
        super(name);
    }

    public Administrator() {}

    @Override
    @Transient
    public List<String> getPermissions() {
        return List.of("tmp_administrator");
    }

}
