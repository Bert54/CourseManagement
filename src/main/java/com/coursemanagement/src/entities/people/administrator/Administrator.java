package com.coursemanagement.src.entities.people.administrator;

import com.coursemanagement.src.entities.people.Person;
import com.coursemanagement.src.entities.people.PersonRoleEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

import java.util.List;

@Entity
@DiscriminatorValue("ADMINISTRATOR")
public class Administrator extends Person {

    public Administrator(String name) {
        super(name);
        //this.role = PersonRoleEnum.ADMINISTRATOR;
    }

    public Administrator() {

    }

    @Override
    @Transient
    public List<String> getPermissions() {
        return List.of("tmp_administrator");
    }

}
