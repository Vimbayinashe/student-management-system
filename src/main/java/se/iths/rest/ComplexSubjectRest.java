package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.exceptions.IdNumberNotFoundException;
import se.iths.exceptions.NestedIdNumberNotFoundException;
import se.iths.service.StudentService;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.Set;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ComplexSubjectRest {

    @Inject
    SubjectService subjectService;

    @Inject
    StudentService studentService;

    @Path("{id}/students")
    @GET
    public Response getStudents(@PathParam("id") Long id) {
        Optional<Subject> foundSubject = subjectService.getById(Subject.class, id);
        Subject subject = foundSubject.orElseThrow(() -> new IdNumberNotFoundException("subject", id));
        Set<Student> students = subject.getStudents();

        return Response.ok(students).build();
    }

    @Path("{subjectId}/students/{studentId}")
    @GET
    public Response getStudentById(@PathParam("subjectId") Long subjectId, @PathParam("studentId") Long studentId) {
        Optional<Subject> foundSubject = subjectService.getById(Subject.class, subjectId);
        Subject subject = foundSubject.orElseThrow(() -> new IdNumberNotFoundException("subject", subjectId));

        Optional<Student> foundStudent = subject.getStudents().stream().filter(student -> student.getId().equals(studentId)).findFirst();
        Student student = foundStudent
                .orElseThrow(() -> new NestedIdNumberNotFoundException(subjectId, "student", studentId));

        return Response.ok(student).build();
    }

    @Path("{id}/students/{studentId}")
    @PUT
    public Response addStudentToSubject(@PathParam("id") Long subjectId, @PathParam("studentId") Long studentId) {
        Optional<Subject> foundSubject = subjectService.getById(Subject.class, subjectId);
        Subject subject = foundSubject.orElseThrow(() -> new IdNumberNotFoundException("subject", subjectId));

        Optional<Student> foundStudent = studentService.getById(Student.class, studentId);
        Student student = foundStudent.orElseThrow(() -> new IdNumberNotFoundException("student", studentId));

        subject.addStudent(student);

        return Response.ok(subject).build();
    }

    @Path("{id}/students/{studentId}")
    @DELETE
    public Response removeStudentFromSubject(@PathParam("id") Long subjectId, @PathParam("studentId") Long studentId) {
        Optional<Subject> foundSubject = subjectService.getById(Subject.class, subjectId);
        Subject subject = foundSubject.orElseThrow(() -> new IdNumberNotFoundException("subject", subjectId));

        Optional<Student> foundStudent = subject.getStudents().stream().filter(student -> student.getId().equals(studentId)).findFirst();
        Student student = foundStudent
                .orElseThrow(() -> new NestedIdNumberNotFoundException(subjectId, "student", studentId));

        subject.removeStudent(student);

        return Response.status(Response.Status.NO_CONTENT).build();
    }



}
