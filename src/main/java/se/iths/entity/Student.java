package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student extends Person{

    @ManyToMany(mappedBy = "students", cascade = CascadeType.PERSIST)
    private Set<Subject> subjects;

    public Student() {
        subjects = new HashSet<>();
    }

    public Student(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
        subjects = new HashSet<>();
    }
    public Student(String firstName, String lastName, String email, String phoneNumber) {
        super(firstName, lastName, email, phoneNumber);
        subjects = new HashSet<>();
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void removeSubject(Subject subject) {
        subjects.remove(subject);
    }

    @JsonbTransient   //todo: use JPQL queries to show a student's subjects
    public Set<Subject> getSubjects() {
        return Collections.unmodifiableSet(subjects);
    }

}
