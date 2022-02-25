package se.iths.exceptions;

import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class ErrorMessages {


    LocalDateTime timestamp = LocalDateTime.now();
    int errorCode;
    String url;
    List<String> errorMessages;

    public ErrorMessages() {
    }


    public ErrorMessages(Response.Status status, List<String> errorMessages, String url) {
        this.errorCode = status.getStatusCode();
        this.errorMessages = errorMessages;
        this.url = url;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getErrorMessages() {
        return Collections.unmodifiableList(errorMessages);
    }

}
