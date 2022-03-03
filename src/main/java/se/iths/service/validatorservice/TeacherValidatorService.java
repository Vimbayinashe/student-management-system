package se.iths.service.validatorservice;

import se.iths.entity.Teacher;
import se.iths.exceptions.EntityNotFoundException;
import se.iths.service.TeacherService;

import javax.inject.Inject;

public class TeacherValidatorService extends ValidatorService {

    @Inject
    TeacherService teacherService;

    public void validateId(Long id) {
        teacherService.getById(Teacher.class, id).orElseThrow(() -> new EntityNotFoundException("teacher", id));
    }
}
