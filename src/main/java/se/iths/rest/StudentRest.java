package se.iths.rest;


import se.iths.entity.Student;
import se.iths.entity.StudentDetails;
import se.iths.exceptions.IncorrectStudentDetailsException;
import se.iths.exceptions.StudentNotFoundException;
import se.iths.service.StudentService;
import se.iths.service.StudentValidatorService;

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
                ? studentService.getAllStudents()
                : studentService.getStudentsByLastName(lastName);

        return Response.ok(studentList).build();
    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        Optional<Student> foundStudent = studentService.getStudentById(id);
        Student student = foundStudent.orElseThrow(() -> new StudentNotFoundException(id));
        return Response.ok(student).build();
    }

    @Path("")
    @POST
    public Response createStudent(Student student) {
            studentService.createStudent(student);
            return Response.status(Response.Status.CREATED).entity(student)
                    .location(URI.create("/student-management-system/api/v1/students/" + student.getId()))
                    .build();
    }

    @Path("")
    @PUT
    public Response updateStudent(Student student) {
        validatorService.validateId(student.getId());

        //potential errors - invalid student details -> use Validator in StudentService;

        try {
            studentService.updateStudent(student);
            return Response.ok(student).build();
        } catch (RuntimeException e) {
            throw new IncorrectStudentDetailsException("Incorrect details for a student submitted to update.");
        }
    }


    @Path("{id}")
    @PATCH
    public Response updateStudentDetails(@PathParam("id") Long id, StudentDetails studentDetails) {

        // todo: validate that all required fields are submitted?

        Student student = studentService.getStudentById(id).orElseThrow(() -> new StudentNotFoundException(id));

        if (studentDetails.getFirstName() != null)
            student = studentService.updateFirstname(id, studentDetails.getFirstName());
        if (studentDetails.getLastName() != null)
            student = studentService.updateLastName(id, studentDetails.getLastName());
        if (studentDetails.getEmail() != null)
            student = studentService.updateEmail(id, studentDetails.getEmail());
        if (studentDetails.getPhoneNumber() != null)
            student = studentService.updatePhoneNumber(id, studentDetails.getPhoneNumber());

        return Response.ok(student).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        validatorService.validateId(id);
        studentService.deleteStudent(id);
        return Response.status(Response.Status.OK).build();
    }

}
