package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Teacher;
import se.iths.service.ComplexSubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    @Path("{subjectId}/students/{studentId}")
    @PUT
    public Response addStudentToSubject(@PathParam("subjectId") Long subjectId, @PathParam("studentId") Long studentId) {
        service.addStudent(subjectId, studentId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Path("{subjectId}/students/{studentId}")
    @DELETE
    public Response removeStudentFromSubject(@PathParam("subjectId") Long subjectId, @PathParam("studentId") Long studentId) {
        service.removeStudent(subjectId, studentId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Path("{id}/teachers")
    @GET
    public Response getTeacher(@PathParam("id") Long id) {
        Teacher teacher = service.getTeacherFromSubject(id);
        return Response.ok(teacher).build();
    }


    @Path("{subjectId}/teachers/{teacherId}")
    @PUT
    public Response addTeacherToSubject(@PathParam("subjectId") Long subjectId, @PathParam("teacherId") Long teacherId) {
        service.addTeacher(subjectId, teacherId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Path("{subjectId}/teachers/{teacherId}")
    @DELETE
    public Response removeTeacherFromSubject(@PathParam("subjectId") Long subjectId, @PathParam("teacherId") Long teacherId) {
        service.removeTeacher(subjectId, teacherId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
