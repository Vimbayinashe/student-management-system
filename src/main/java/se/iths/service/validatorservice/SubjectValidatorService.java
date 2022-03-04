package se.iths.service.validatorservice;

import se.iths.entity.Subject;
import se.iths.exceptions.IdNumberNotFoundException;
import se.iths.service.SubjectService;

import javax.inject.Inject;

public class SubjectValidatorService extends ValidatorService {

    @Inject
    SubjectService subjectService;

    public void validateId(Long id) {
        subjectService.getById(Subject.class, id).orElseThrow(() -> new IdNumberNotFoundException("subject", id));
    }
}
