package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
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

    @Inject
    TeacherService teacherService;


    public Set<Student> getAllStudents(Long id) {
        Optional<Subject> foundSubject = subjectService.getById(Subject.class, id);
        Subject subject = foundSubject.orElseThrow(() -> new IdNumberNotFoundException("subject", id));
        return subject.getStudents();
    }

    private Subject getSubject(Long subjectId) {
        Optional<Subject> foundSubject = subjectService.getById(Subject.class, subjectId);
        return foundSubject.orElseThrow(() -> new IdNumberNotFoundException("subject", subjectId));
    }

    private Student getStudent(Long studentId) {
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

    public void removeStudent(Long subjectId, Long studentId) {
        Subject subject = getSubject(subjectId);
        Student student = getStudentFromSubject(subjectId, studentId);
        subject.removeStudent(student);
        subjectService.update(subject);
    }

    public void addStudent(Long subjectId, Long studentId) {
        Subject subject = getSubject(subjectId);
        Student student = getStudent(studentId);
        subject.addStudent(student);
        subjectService.update(subject);
        studentService.update(student);
    }

    public Teacher getTeacherFromSubject(Long id) {
        Subject subject = getSubject(id);
        Optional<Teacher> teacher = Optional.ofNullable(subject.getTeacher());
        return teacher.orElse(new Teacher());
    }

    public void addTeacher(Long subjectId, Long teacherId) {
        Subject subject = getSubject(subjectId);
        Teacher teacher = getTeacher(teacherId);
        subject.setTeacher(teacher);
        teacher.addSubject(subject);
        subjectService.update(subject);
    }

    private Teacher getTeacher(Long id) {
        Optional<Teacher> foundTeacher = teacherService.getById(Teacher.class, id);
        return foundTeacher.orElseThrow(() -> new IdNumberNotFoundException("teacher", id));
    }

    public void removeTeacher(Long subjectId, Long teacherId) {
        Subject subject = getSubject(subjectId);
        Optional<Teacher> foundTeacher = Optional.ofNullable(subject.getTeacher());
        Teacher teacher = foundTeacher.orElseThrow(
                () -> new NestedIdNumberNotFoundException(subjectId, "teacher", teacherId));

        subject.removeTeacher();
        teacher.removeSubject(subject);
        teacherService.update(teacher);
        subjectService.update(subject);
    }
}
