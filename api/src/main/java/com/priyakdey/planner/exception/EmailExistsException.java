package com.priyakdey.planner.exception;

import java.io.Serial;

/**
 * @author Priyak Dey
 */
public class EmailExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2381850899515305513L;

    public EmailExistsException(String message) {
        super(message);
    }
}
