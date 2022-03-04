package se.iths.service;


import se.iths.entity.Person;
import se.iths.entity.Student;
import se.iths.entity.Teacher;
import se.iths.exceptions.IncorrectPersonDetailsException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
public abstract class Service {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    Validator validator;

    public <T> void create(T entity) {
        if (entity instanceof Person)
            validatePerson((Person) entity);
        entityManager.persist(entity);
    }

    public <T> Optional<T> getById(Class<T> subClass, Long id) {
        return Optional.ofNullable(entityManager.find(subClass, id));
    }

    public <T> List<T> getAll(Class<T> subClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(subClass);
        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    public <T> void update(T entity) {
        if (entity instanceof Person)
            validatePerson((Person) entity);
        entityManager.merge(entity);
    }

    public <T extends Person> T updateFirstname(Class<T> subClass, Long id, String firstname) {
        T person = entityManager.find(subClass, id);
        person.setFirstName(firstname);
        return person;
    }

    public <T extends Person> T updateLastName(Class<T> subClass, Long id, String lastName) {
        T person = entityManager.find(subClass, id);
        person.setLastName(lastName);
        return person;
    }

    public <T extends Person> T updateEmail(Class<T> subClass, Long id, String email) {
        T person = entityManager.find(subClass, id);
        person.setEmail(email);
        return person;
    }

    public <T extends Person> T updatePhoneNumber(Class<T> subClass, Long id, String phoneNumber) {
        T person = entityManager.find(subClass, id);
        person.setPhoneNumber(phoneNumber);
        return person;
    }

    public <T> void delete(Class<T> subClass, Long id) {
        T entity = entityManager.find(subClass, id);
        entityManager.remove(entity);
    }

    public <T extends Person> List<T> getPersonsByLastname(Class<T> subClass, String lastName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(subClass);

        Root<T> root = criteriaQuery.from(subClass);
        criteriaQuery.select(root).where(criteriaBuilder.like(root.get("lastName"), lastName));

        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    private <T extends Person> void validatePerson(T person) {
        Set<ConstraintViolation<T>> violations = validator.validate(person);
        List<String> errorMessages =
                violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        handleErrors(person, errorMessages);
    }

    private <T extends Person> void handleErrors(T person, List<String> errorMessages) {
        if (errorMessages.size() > 0 && person instanceof Student)
            throw new IncorrectPersonDetailsException(errorMessages, "students");
        if (errorMessages.size() > 0 && person instanceof Teacher)
            throw new IncorrectPersonDetailsException(errorMessages, "teachers");
    }

}
