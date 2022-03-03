package se.iths.service;


import se.iths.entity.Person;
import se.iths.entity.Student;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class Service {

    @PersistenceContext
    EntityManager entityManager;

    public <T> void create(T entity) {
        entityManager.persist(entity);
    }


    public <T> Optional<T> getById(Class<T> subClass, Long id) {
        return Optional.ofNullable(entityManager.find(subClass, id));
    }

    public <T> List<T> getAll(Class<T> subClass) {
//        return entityManager.createQuery("SELECT s FROM Student s", subClass).getResultList();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(subClass);
        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    public <T> void update(T entity) {
        entityManager.merge(entity);
    }


    public <T extends Person> T updateFirstname(Class<T> subClass, Long id, String firstname) {
        T person = entityManager.find(subClass, id);
        person.setFirstName(firstname);
        return person;
    }

    public <T extends Person> T  updateLastName(Class<T> subClass, Long id, String lastName) {
        T person = entityManager.find(subClass, id);
        person.setLastName(lastName);
        return person;
    }

    public <T extends Person> T  updateEmail(Class<T> subClass, Long id, String email) {
        T person = entityManager.find(subClass, id);
        person.setEmail(email);
        return person;
    }

    public <T extends Person> T  updatePhoneNumber(Class<T> subClass, Long id, String phoneNumber) {
        T person = entityManager.find(subClass, id);
        person.setPhoneNumber(phoneNumber);
        return person;
    }


    public <T>  void delete(Class<T> subClass, Long id) {
        T entity = entityManager.find(subClass, id);
        entityManager.remove(entity);
    }


//    public List<Student> getStudentsByLastName(String lastName) {
//        return entityManager.createQuery("SELECT s FROM Student s WHERE s.lastName = :lastName", Student.class)
//                .setParameter("lastName", lastName)
//                .getResultList();
//    }

}
