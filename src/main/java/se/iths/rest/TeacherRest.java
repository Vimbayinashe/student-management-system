package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Teacher;
import se.iths.exceptions.StudentNotFoundException;
import se.iths.service.StudentValidatorService;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @Path("")
    @GET
    public Response getAllTeachers(@QueryParam("lastName") String lastName) {
        List<Teacher> teacherList = lastName == null
                ? teacherService.getAll(Teacher.class)
                : teacherService.getPersonsByLastname(Teacher.class, lastName);
        return Response.ok(teacherList).build();
    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        Optional<Teacher> foundStudent = teacherService.getById(Teacher.class, id);
        Teacher teacher = foundStudent.orElseThrow(() -> new StudentNotFoundException(id));
        return Response.ok(teacher).build();
    }

    @Path("")
    @POST
    public Response createTeacher(Teacher teacher) {
        teacherService.create(teacher);
        return Response.status(Response.Status.CREATED).entity(teacher)
                .location(URI.create("/student-management-system/api/v1/teachers/" + teacher.getId()))
                .build();
    }

    @Path("")
    @PUT
    public Response updateTeacher(Teacher teacher) {
//        validatorService.validateId(teacher.getId());
        teacherService.update(teacher);
        return Response.ok(teacher).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long id) {
//        validatorService.validateId(id);
        teacherService.delete(Teacher.class, id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
