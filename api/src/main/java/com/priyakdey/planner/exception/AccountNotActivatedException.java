package com.priyakdey.planner.exception;

import java.io.Serial;

/**
 * @author Priyak Dey
 */
public class AccountNotActivatedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -7122745121124121468L;

    public AccountNotActivatedException(String message) {
        super(message);
    }
}
