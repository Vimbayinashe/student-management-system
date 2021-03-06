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

    public ErrorMessage() {
    }

    public ErrorMessage(Response.Status status, String message, String url) {
        this.errorCode = status.getStatusCode();
        this.message = message;
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

}
