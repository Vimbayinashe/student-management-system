package se.iths.rest;


import se.iths.entity.Student;
import se.iths.entity.PersonDetails;
import se.iths.exceptions.IdNumberNotFoundException;
import se.iths.service.StudentService;
import se.iths.service.validatorservice.StudentValidatorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @Inject
    StudentValidatorService validatorService;

    @Path("")
    @GET
    public Response getAllStudents(@QueryParam("lastName") String lastName) {
        List<Student> studentList = lastName == null
                ? studentService.getAll(Student.class)
                : studentService.getPersonsByLastname(Student.class, lastName);

        return Response.ok(studentList).build();
    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        Optional<Student> foundStudent = studentService.getById(Student.class, id);
        Student student = foundStudent.orElseThrow(() -> new IdNumberNotFoundException("student", id));
        return Response.ok(student).build();
    }

    @Path("")
    @POST
    public Response createStudent(Student student) {
        studentService.create(student);
        return Response.status(Response.Status.CREATED).entity(student)
                .location(URI.create("/student-management-system/api/v1/students/" + student.getId()))
                .build();
    }

    @Path("")
    @PUT
    public Response updateStudent(Student student) {
        validatorService.validateId(student.getId());
        studentService.update(student);
        return Response.ok(student).build();
    }


    @Path("{id}")
    @PATCH
    public Response updateStudentDetails(@PathParam("id") Long id, PersonDetails personDetails) {
        Student student =
                studentService.getById(Student.class, id).orElseThrow(() -> new IdNumberNotFoundException("student", id));

        if (validatorService.isUpdated(personDetails.getFirstName()))
            student = studentService.updateFirstname(Student.class, id, personDetails.getFirstName());
        if (validatorService.isUpdated(personDetails.getLastName()))
            student = studentService.updateLastName(Student.class, id, personDetails.getLastName());
        if (validatorService.isUpdated(personDetails.getEmail()))
            student = studentService.updateEmail(Student.class, id, personDetails.getEmail());
        if (validatorService.isUpdated(personDetails.getPhoneNumber()))
            student = studentService.updatePhoneNumber(Student.class, id, personDetails.getPhoneNumber());

        return Response.ok(student).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        validatorService.validateId(id);
        studentService.delete(Student.class, id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
