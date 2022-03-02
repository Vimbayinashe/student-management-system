package se.iths.utils;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
@Startup
public class SampleDataGenerator {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateSampleData() {
        List<Subject> subjects = List.of(
                new Subject("mathematics"),
                new Subject("english"),
                new Subject("chemistry")
        );

        List<Student> students = List.of(
                new Student("Mike", "Thomas", "mike@thomasandsons.com"),
                new Student("Lewis", "Thomas", "lewis@thomasandsons.com"),
                new Student("Elizabeth", "Stewart", "e.stewart@mymail.com"),
                new Student("Henry", "Thorbjörn", "henry_b@mymail.com"),
                new Student("Francis", "Everton", "francis@evertonfc.com")
        );

        Teacher teacher1 = new Teacher("Alice", "Thomas", "alice@thomasandsons.com");
        Teacher teacher2 = new Teacher("Louis", "Tomaz", "ltomaz@mymail.com");

        students.forEach(student -> subjects.forEach(subject -> subject.addStudent(student)));

        teacher1.addSubject(subjects.get(0));
        teacher1.addSubject(subjects.get(1));
        teacher2.addSubject(subjects.get(2));

        subjects.forEach(subject -> entityManager.persist(subject));

        // entityManager.persist(mathematics);  // can persist both subject & students (by itself).
                                            // Students are only created IF they don't already exist

    }
}
