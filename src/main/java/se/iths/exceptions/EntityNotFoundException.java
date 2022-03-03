package se.iths.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class EntityNotFoundException extends WebApplicationException {

    public EntityNotFoundException(String entity, Long id)    {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorMessage(Response.Status.NOT_FOUND, entity + " with Id: " + id + " not found.",
                        "/api/v1/" + entity + "s/" + id))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build());
    }

}
