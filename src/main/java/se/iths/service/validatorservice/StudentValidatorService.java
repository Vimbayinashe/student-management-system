package se.iths.service.validatorservice;

import se.iths.entity.Student;
import se.iths.exceptions.IdNumberNotFoundException;
import se.iths.service.StudentService;

import javax.inject.Inject;

public class StudentValidatorService extends  ValidatorService {

    @Inject
    StudentService studentService;

    public void validateId(Long id) {
        studentService.getById(Student.class, id).orElseThrow(() -> new IdNumberNotFoundException("student", id));
    }

}
