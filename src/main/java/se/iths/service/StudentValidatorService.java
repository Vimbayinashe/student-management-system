package se.iths.service;

import se.iths.exceptions.StudentNotFoundException;

import javax.inject.Inject;

public class StudentValidatorService {

    @Inject
    StudentService studentService;

    public void validateId(Long id) {
        studentService.getStudentById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    public boolean isUpdated(String value) {
        if(value == null)
            return false;
        return  value.length() > 0;
    }
}
