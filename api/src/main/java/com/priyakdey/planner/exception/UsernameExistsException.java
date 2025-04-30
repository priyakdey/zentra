package com.priyakdey.planner.exception;

import java.io.Serial;

/**
 * @author Priyak Dey
 */
public class UsernameExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 6148989314630106559L;

    public UsernameExistsException(String message) {
        super(message);
    }
}
