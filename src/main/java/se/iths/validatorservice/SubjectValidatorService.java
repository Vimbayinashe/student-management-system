package se.iths.validatorservice;

import se.iths.entity.Subject;
import se.iths.exceptions.EntityNotFoundException;
import se.iths.service.SubjectService;

import javax.inject.Inject;

public class SubjectValidatorService extends ValidatorService {

    @Inject
    SubjectService subjectService;

    public void validateId(Long id) {
        subjectService.getById(Subject.class, id).orElseThrow(() -> new EntityNotFoundException("subject", id));
    }
}
