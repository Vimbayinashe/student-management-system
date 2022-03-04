package se.iths.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NestedIdNumberNotFoundException extends WebApplicationException {

    public NestedIdNumberNotFoundException(Long id, String entity, Long entityId)    {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorMessage(Response.Status.NOT_FOUND,
                            entity + " with ID " + entityId + " not found in this subject.",
                            "/api/v1/subjects/" + id + "/" + entity + "s/" + entityId))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build());
    }

}
