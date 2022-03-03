package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    private Set<Student> students = new HashSet<>();;

    @ManyToOne(cascade = CascadeType.PERSIST)
    Teacher teacher;

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
