package se.iths.service;


import se.iths.entity.Student;
import se.iths.exceptions.IncorrectStudentDetailsException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
public class StudentService extends Service {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    Validator validator;


    public void create(Student student) {
        validateStudent(student);
        super.create(student);
    }

    private void validateStudent(Student student) {
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        List<String> errorMessages =
                violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        if (errorMessages.size() > 0)
            throw new IncorrectStudentDetailsException(errorMessages);
    }


    public void update(Student student) {
        validateStudent(student);
        super.update(student);
    }

}
