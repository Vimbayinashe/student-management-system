package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Teacher extends Person{

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.PERSIST)
    List<Subject> subjects = new ArrayList<>();

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }
    public Teacher(String firstName, String lastName, String email, String phoneNumber) {
        super(firstName, lastName, email, phoneNumber);
    }


    public List<Subject> getSubjects() {
        return Collections.unmodifiableList(subjects);
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void removeSubject(Subject subject) {
        subjects.remove(subject);
    }
}
