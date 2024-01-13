package com.coursemanagement;

import com.coursemanagement.src.manager.MainManager;
import com.coursemanagement.src.manager.PeopleManager.PeopleManager;
import com.coursemanagement.src.manager.PeopleManager.PeopleManagerListFactory;
import com.coursemanagement.src.entities.people.Person;
import com.coursemanagement.src.entities.people.administrator.Administrator;
import com.coursemanagement.src.entities.people.student.Student;
import com.coursemanagement.src.entities.people.teacher.Teacher;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello worl... Bah too classic");

        PeopleManager peopleManager = PeopleManagerListFactory.getInstance().create();

        MainManager manager = new MainManager(peopleManager);

        var name1 = "Boris";
        var name2 = "Natalya";
        var name3 = "James";

        Person p1 = new Teacher(name1);
        Person p2 = new Student(name2);
        Person p3 = new Administrator(name3);

        try {
            manager.addPerson(p1);
        } catch (Exception ignored) { }

        try {
            manager.addPerson(p2);
        } catch (Exception ignored) { }

        try {
            manager.addPerson(p3);
        } catch (Exception ignored) { }

        try {
            Person gottenPerson = manager.getPerson(name3);
            System.out.printf("My name is %s%n", gottenPerson.getName());
        } catch (Exception ignored) { }

    }

}
