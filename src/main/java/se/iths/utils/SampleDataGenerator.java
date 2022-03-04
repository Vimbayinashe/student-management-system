package se.iths.utils;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.service.TeacherService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
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
        subjects.get(0).setTeacher(teacher1);
        teacher1.addSubject(subjects.get(1));
        subjects.get(1).setTeacher(teacher1);
        teacher2.addSubject(subjects.get(2));
        subjects.get(2).setTeacher(teacher2);

        subjects.forEach(subject -> entityManager.persist(subject));

        List<Student> students2 = List.of(
                new Student("Ernest", "Machingaidze", "ernie@myemail.com"),
                new Student("Lionel", "Cunningham", "lionel.c@mymail.com"),
                new Student("Trevor", "Williams", "twilliams@mymail.com")
        );

        students2.forEach(student -> entityManager.persist(student));

        List<Subject> subjects2 = List.of(
                new Subject("geography"),
                new Subject("history"),
                new Subject("art")
        );

        subjects2.forEach(subject -> entityManager.persist(subject));

        List<Teacher> teachers = List.of(
                new Teacher("Michael", "Taylor", "taylor_man@myemail.com"),
                new Teacher("Per", "Simpson", "pers@mymail.com"),
                new Teacher("Miley", "Sunboom", "miss_moon@mymail.com", "0732345678")
        );

        teachers.forEach(teacher -> entityManager.persist(teacher));
    }
}
