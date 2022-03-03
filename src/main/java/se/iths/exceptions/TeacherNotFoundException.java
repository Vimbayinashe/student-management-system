package se.iths.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TeacherNotFoundException extends WebApplicationException {

    public TeacherNotFoundException(Long id)    {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorMessage(Response.Status.NOT_FOUND, "Teacher with ID " + id + " not found",
                        "/api/v1/teachers/" + id))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build());
    }

}
