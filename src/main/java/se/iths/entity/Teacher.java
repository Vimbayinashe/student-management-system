package se.iths.entity;

import javax.persistence.Entity;

@Entity
public class Teacher extends Person{

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }
    public Teacher(String firstName, String lastName, String email, String phoneNumber) {
        super(firstName, lastName, email, phoneNumber);
    }
}
