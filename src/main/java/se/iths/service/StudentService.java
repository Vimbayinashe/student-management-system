package se.iths.service;


import se.iths.entity.Student;
import se.iths.exceptions.IncorrectStudentDetailsException;
import se.iths.exceptions.StudentNotFoundException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    Validator validator;


    Logger logger = Logger.getLogger(StudentService.class.getName());

    public void createStudent(Student student) {
        validateNewStudent(student);
        entityManager.persist(student);
    }

    private void validateNewStudent(Student student) {
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        List<String> errorMessages =
                violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        if (errorMessages.size() > 0)
            throw new IncorrectStudentDetailsException(errorMessages);
    }

    public Optional<Student> getStudentById(Long id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }

    public List<Student> getAllStudents() {
        return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    public void updateStudent(Student student) {
//        validatorService.validateId(student.getId());
//        Student foundStudent =
//                getStudentById(student.getId()).orElseThrow(() -> new StudentNotFoundException(student.getId()));
        logger.log(Level.WARNING, student.getId().toString());

        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        List<String> errorMessages =
                violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        if (errorMessages.size() > 0)
            throw new IncorrectStudentDetailsException(errorMessages);

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

    public List<Student> getStudentsByLastName(String lastName) {
        return entityManager.createQuery("SELECT s FROM Student s WHERE s.lastName = :lastName", Student.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }

}
