package se.iths.rest;

import se.iths.entity.PersonDetails;
import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.exceptions.EntityNotFoundException;
import se.iths.exceptions.StudentNotFoundException;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @Path("")
    @GET
    public Response getAllSubjects() {
        List<Subject> subjects = subjectService.getAll(Subject.class);
        return Response.ok(subjects).build();
    }


    @Path("{id}")
    @GET
    public Response getSubject(@PathParam("id") Long id) {
        Optional<Subject> foundSubject = subjectService.getById(Subject.class, id);
        Subject subject = foundSubject.orElseThrow(() -> new EntityNotFoundException("subject", id));
        return Response.ok(subject).build();
    }

    @Path("")
    @POST
    public Response createSubject(Subject subject) {
        subjectService.create(subject);
        return Response.status(Response.Status.CREATED).entity(subject)
                .location(URI.create("/student-management-system/api/v1/subjects/" + subject.getId()))
                .build();
    }

}
