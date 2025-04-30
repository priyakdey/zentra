package com.priyakdey.planner.model.response;

import java.io.Serial;
import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDateTime;

/**
 * @author Priyak Dey
 */
public class ErrorResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 3225556529224179005L;

    private int status;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now(Clock.systemUTC());
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}
