package se.iths.rest;

import se.iths.entity.PersonDetails;
import se.iths.entity.Teacher;
import se.iths.exceptions.TeacherNotFoundException;
import se.iths.service.TeacherService;
import se.iths.service.validatorservice.TeacherValidatorService;

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

    @Inject
    TeacherValidatorService validatorService;

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
    public Response getTeacher(@PathParam("id") Long id) {
        Optional<Teacher> foundTeacher = teacherService.getById(Teacher.class, id);
        Teacher teacher = foundTeacher.orElseThrow(() -> new TeacherNotFoundException(id));
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
        validatorService.validateId(teacher.getId());
        teacherService.update(teacher);
        return Response.ok(teacher).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateTeacher(@PathParam("id") Long id, PersonDetails personDetails) {
        Teacher teacher =
                teacherService.getById(Teacher.class, id).orElseThrow(() -> new TeacherNotFoundException(id));

        if (validatorService.isUpdated(personDetails.getFirstName()))
            teacher = teacherService.updateFirstname(Teacher.class, id, personDetails.getFirstName());
        if (validatorService.isUpdated(personDetails.getLastName()))
            teacher = teacherService.updateLastName(Teacher.class, id, personDetails.getLastName());
        if (validatorService.isUpdated(personDetails.getEmail()))
            teacher = teacherService.updateEmail(Teacher.class, id, personDetails.getEmail());
        if (validatorService.isUpdated(personDetails.getPhoneNumber()))
            teacher = teacherService.updatePhoneNumber(Teacher.class, id, personDetails.getPhoneNumber());

        return Response.ok(teacher).build();
    }


    @Path("{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long id) {
        validatorService.validateId(id);
        teacherService.delete(Teacher.class, id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
