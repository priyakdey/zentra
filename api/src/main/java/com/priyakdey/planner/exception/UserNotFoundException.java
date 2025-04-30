package com.priyakdey.planner.exception;

import java.io.Serial;

/**
 * @author Priyak Dey
 */
public class UserNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -7521148588447928570L;

    public UserNotFoundException(String message) {
        super(message);
    }
}
