package com.priyakdey.zentra.model.request;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Priyak Dey
 */
public class UserLoginRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -4052468303811525908L;

    private String username;
    private char[] password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}
