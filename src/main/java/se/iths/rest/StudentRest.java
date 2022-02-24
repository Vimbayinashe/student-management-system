package se.iths.rest;


import se.iths.entity.Student;
import se.iths.exceptions.StudentNotFoundException;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

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
    public Response createStudent(Student student) {    //todo: is it better to use Optional here?
        try {
            studentService.createStudent(student);
            return Response.status(Response.Status.CREATED).entity(student).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorMessage(Response.Status.BAD_REQUEST, "Incorrect student details submitted."))
                    .build();
//            throw new IncorrectStudentDetailsException(e);
        }
    }

    //todo : is creating a new entity instead of replacing!!
    @Path("")
    @PUT
    public Response updateStudent(Student student) {
        studentService.updateStudent(student);
        return Response.ok(student).build();
    }

    // Exception handling: should be a StudentNotFoundException in a try catch block


    @Path("{id}")
    @PATCH
    public Response updateStudentDetails(@PathParam("id") Long id,
                                         @QueryParam("firstName") String firstName,
                                         @QueryParam("lastName") String lastName,
                                         @QueryParam("email") String email,
                                         @QueryParam("phoneNumber") String phoneNumber
    ) {

        Student student = studentService.getStudentById(id).orElseThrow(() -> new StudentNotFoundException(id));

        if (firstName != null)
            student = studentService.updateFirstname(id, firstName);
        if (lastName != null)
            student = studentService.updateLastName(id, lastName);
        if (email != null)
            student = studentService.updateEmail(id, email);
        if (phoneNumber != null)
            student = studentService.updatePhoneNumber(id, phoneNumber);

        return Response.ok(student).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        checkIfStudentExists(id);
        studentService.deleteStudent(id);
        return Response.status(Response.Status.OK)
                .entity("{\"message\": \"Student successfully deleted\"}")
                .build();
    }

    private void checkIfStudentExists(Long id) {
        Student student = studentService.getStudentById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

}
