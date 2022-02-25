package se.iths.service;

import se.iths.exceptions.StudentNotFoundException;

import javax.inject.Inject;

public class StudentValidatorService {

    @Inject
    StudentService studentService;

    public void validateId(Long id) {
        studentService.getStudentById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }
}
