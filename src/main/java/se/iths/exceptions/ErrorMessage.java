package se.iths.exceptions;

import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class ErrorMessage {

    LocalDateTime timestamp = LocalDateTime.now();
    int errorCode;
    String message;
    String url;
    List <String> errorMessages;

    public ErrorMessage() {
    }

    public ErrorMessage(Response.Status status, String message, String url) {
        this.errorCode = status.getStatusCode();
        this.message = message;
        this.url = url;
    }
    public ErrorMessage(Response.Status status, List<String> errorMessages, String url) {
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

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getErrorMessages() {
        return Collections.unmodifiableList(errorMessages);
    }
}
