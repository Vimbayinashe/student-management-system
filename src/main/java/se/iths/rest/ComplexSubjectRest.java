package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.exceptions.IdNumberNotFoundException;
import se.iths.exceptions.NestedIdNumberNotFoundException;
import se.iths.service.ComplexSubjectService;
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
    ComplexSubjectService service;

    @Path("{id}/students")
    @GET
    public Response getStudents(@PathParam("id") Long id) {
        Set<Student> students = service.getAllStudents(id);
        return Response.ok(students).build();
    }

    @Path("{subjectId}/students/{studentId}")
    @GET
    public Response getStudentById(@PathParam("subjectId") Long subjectId, @PathParam("studentId") Long studentId) {
        Student student = service.getStudentFromSubject(subjectId, studentId);
        return Response.ok(student).build();
    }

    @Path("{id}/students/{studentId}")
    @PUT
    public Response addStudentToSubject(@PathParam("id") Long subjectId, @PathParam("studentId") Long studentId) {
        service.addStudent(subjectId, studentId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Path("{id}/students/{studentId}")
    @DELETE
    public Response removeStudentFromSubject(@PathParam("id") Long subjectId, @PathParam("studentId") Long studentId) {
        service.removeStudent(subjectId, studentId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
