package se.iths.utils;

import se.iths.entity.Student;

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

        students.forEach(student -> entityManager.persist(student));
    }
}
