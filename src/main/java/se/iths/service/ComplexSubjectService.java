package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.exceptions.IdNumberNotFoundException;
import se.iths.exceptions.NestedIdNumberNotFoundException;

import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;

public class ComplexSubjectService {

    @Inject
    SubjectService subjectService;

    @Inject
    StudentService studentService;


    public Set<Student> getAllStudents(Long id) {
        Optional<Subject> foundSubject = subjectService.getById(Subject.class, id);
        Subject subject = foundSubject.orElseThrow(() -> new IdNumberNotFoundException("subject", id));
        return subject.getStudents();
    }

    public Subject getSubject(Long subjectId) {
        Optional<Subject> foundSubject = subjectService.getById(Subject.class, subjectId);
        return foundSubject.orElseThrow(() -> new IdNumberNotFoundException("subject", subjectId));
    }

    public Student getStudent(Long studentId) {
        Optional<Student> foundStudent = studentService.getById(Student.class, studentId);
        return foundStudent.orElseThrow(() -> new IdNumberNotFoundException("student", studentId));
    }

    public Student getStudentFromSubject(Long subjectId, Long studentId) {
        Subject subject = getSubject(subjectId);
        Optional<Student> foundStudent = subject.getStudents().stream()
                .filter(student -> student.getId().equals(studentId))
                .findFirst();
        return foundStudent.orElseThrow(() -> new NestedIdNumberNotFoundException(subjectId, "student", studentId));
    }

}
