package se.iths.exceptions;

import javax.ws.rs.WebApplicationException;

public class StudentNotFoundException extends WebApplicationException {
    private Long id;

    public StudentNotFoundException()    {
    }

    public StudentNotFoundException(Long id)    {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
