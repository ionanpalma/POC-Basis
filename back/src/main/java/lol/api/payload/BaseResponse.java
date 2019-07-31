package lol.api.payload;

import java.time.Instant;

import org.springframework.http.HttpStatus;

public class BaseResponse {

    private Instant timestamp;
    private Integer status;
    private String statusMessage;
    private Object message;
    private String path;

    public BaseResponse() {
    }

    public BaseResponse(Object message, HttpStatus status, String path) {
        this.message = message;
        this.status = status.value();
        this.path = path;
        this.timestamp = Instant.now();
        this.statusMessage = this.customStatusMessage();
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public Object getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public Integer getStatus() {
        return status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    private String customStatusMessage() {
        String message = null;

        switch (this.status) {
        case 200:
            message = "OK";
            break;
        case 201:
            message = "Created";
            break;
        case 400:
            message = "Bad Request";
            break;
        case 401:
            message = "Unauthorized";
            break;
        case 403:
            message = "Forbidden";
            break;
        case 404:
            message = "Not Found";
            break;
        case 405:
            message = "Method Not Allowed";
            break;
        case 500:
            message = "Internal Server Error";
            break;
        case 409:
            message = "Conflict";
            break;
        }

        return message;
    }
}