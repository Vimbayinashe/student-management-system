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
    private Set<Subject> subjects = new HashSet<>();

    public Student() {
    }

    public Student(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }
    public Student(String firstName, String lastName, String email, String phoneNumber) {
        super(firstName, lastName, email, phoneNumber);
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void removeSubject(Subject subject) {
        subjects.remove(subject);
    }

    @JsonbTransient
    public Set<Subject> getSubjects() {
        return Collections.unmodifiableSet(subjects);
    }

}
