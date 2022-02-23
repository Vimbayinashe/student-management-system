package se.iths.rest;


import se.iths.entity.Student;
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
    public Response getAllStudents() {
        List<Student> studentList = studentService.getAllStudents();
        return Response.ok(studentList).build();
    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        Optional<Student> foundStudent = studentService.getStudentById(id);

        Student student = foundStudent.orElseThrow(     //create own Exception here?
                () -> new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorMessage(Response.Status.NOT_FOUND, "Student with ID " + id + " not found",
                                "/api/v1/students/" + id))
                        .type(MediaType.APPLICATION_JSON_TYPE)
                        .build()
                )
        );
        return Response.ok(student).build();
    }

    @Path("")
    @POST
    public Response createStudent(Student student) {
        studentService.createStudent(student);
        return Response.status(Response.Status.CREATED).entity(student).build();
    }





    // Meaningful Response Codes
    // return Response.status(Response.Status.CREATED).entity(student).build();
    // return Response.created(URI.create("/codes"))     //alternative

    //todo: error handling when student id not found OR just give the generic error message

}
