package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.exceptions.EntityNotFoundException;
import se.iths.service.SubjectService;
import se.iths.validatorservice.SubjectValidatorService;

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

    @Inject
    SubjectValidatorService validatorService;

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

    @Path("")
    @PUT
    public Response update(Subject subject) {
        validatorService.validateId(subject.getId());
        subjectService.update(subject);
        return Response.ok(subject).build();
    }

    @Path("{id}")
    @DELETE
    public Response delete(@PathParam("id") Long id) {
        validatorService.validateId(id);
        subjectService.delete(Subject.class, id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
