package se.iths.entity;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Student> students;

    public Subject() {
        students = new HashSet<>();
    }

    public Subject(String name) {
        this.name = name;
        students = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public Subject setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Subject setName(String name) {
        this.name = name;
        return this;
    }

    public void addStudent(Student student) {
        students.add(student);
        student.addSubject(this);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.removeSubject(this);
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return Objects.equals(id, subject.id) && Objects.equals(name, subject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
