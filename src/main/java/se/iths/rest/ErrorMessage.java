package se.iths.rest;

import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

public class ErrorMessage {

    LocalDateTime timestamp = LocalDateTime.now();
    int errorCode;
    String message;
    String url;

    public ErrorMessage(Response.Status status, String message, String path) {
        this.errorCode = status.getStatusCode();
        this.message = message;
        this.url = path;
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
