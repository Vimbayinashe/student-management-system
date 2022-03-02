package se.iths.utils;

import se.iths.entity.Student;
import se.iths.entity.Subject;

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
        List<Student> students = List.of(
            new Student("Mike", "Thomas", "mike@thomasandsons.com"),
            new Student("Lewis", "Thomas", "mike@thomasandsons.com"),
            new Student("Elizabeth", "Stewart", "e.stewart@mymail.com"),
            new Student("Henry", "Thorbjörn", "henry_b@mymail.com"),
            new Student("Francis", "Everton", "francis@evertonfc.com")
        );

        Subject mathematics = new Subject("mathematics");
        students.forEach(mathematics::addStudent);

        // students.forEach(student -> entityManager.persist(student));    // persists students only
        entityManager.persist(mathematics);  // can persist both subject & students (by itself).
                                            // Students are only created IF they don't already exist

    }
}
