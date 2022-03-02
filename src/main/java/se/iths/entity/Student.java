package se.iths.entity;

import javax.persistence.Entity;

@Entity
public class Student extends Person{

    public Student() {
    }

    public Student(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }
    public Student(String firstName, String lastName, String email, String phoneNumber) {
        super(firstName, lastName, email, phoneNumber);
    }
}
