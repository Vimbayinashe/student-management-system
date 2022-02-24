package se.iths.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class StudentNotFoundExceptionMapper implements ExceptionMapper<StudentNotFoundException> {
    @Override
    public Response toResponse(StudentNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorMessage(Response.Status.NOT_FOUND, "Student with ID " + e.getId() + " not found",
                        "/api/v1/students/" + e.getId()))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
