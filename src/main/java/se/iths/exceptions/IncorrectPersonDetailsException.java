package se.iths.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

public class IncorrectPersonDetailsException extends WebApplicationException {

    public IncorrectPersonDetailsException(List<String > messages, String subdirectory) {
        super(Response.status(BAD_REQUEST)
                .entity(new ErrorMessages(BAD_REQUEST, messages, "/api/v1/" + subdirectory + "/"))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build());
    }

}
