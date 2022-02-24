package se.iths.service;


import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void createStudent(Student student) {
        entityManager.persist(student);
    }

    public Optional<Student> getStudentById(Long id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }

    public List<Student> getAllStudents() {
        return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    public Student updateFirstname(Long id, String firstname) {
        Student student = entityManager.find(Student.class, id);
        student.setFirstName(firstname);
        return student;
    }

    public Student updateLastName(Long id, String lastName) {
        Student student = entityManager.find(Student.class, id);
        student.setLastName(lastName);
        return student;
    }

    public Student updateEmail(Long id, String email) {
        Student student = entityManager.find(Student.class, id);
        student.setEmail(email);
        return student;
    }

    public Student updatePhoneNumber(Long id, String phoneNumber) {
        Student student = entityManager.find(Student.class, id);
        student.setPhoneNumber(phoneNumber);
        return student;
    }

    public void deleteStudent(Long id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }


}
