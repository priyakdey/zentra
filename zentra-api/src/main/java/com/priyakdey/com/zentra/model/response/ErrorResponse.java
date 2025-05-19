package com.priyakdey.com.zentra.model.response;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Priyak Dey
 */
public class ErrorResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 7203334986342460169L;

    private String message;
    private String description;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, String description) {
        this.message = message;
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
