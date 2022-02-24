package se.iths.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public class IncorrectStudentDetailsExceptionMapper implements ExceptionMapper<IncorrectStudentDetailsException> {
    @Override
    public Response toResponse(IncorrectStudentDetailsException e) {
        return Response.status(BAD_REQUEST)
                .entity(new ErrorMessage(BAD_REQUEST, e.getCustomMessage(), "/api/v1/students/"))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
