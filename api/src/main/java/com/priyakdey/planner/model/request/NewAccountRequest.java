package com.priyakdey.planner.model.request;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Priyak Dey
 */
public class NewAccountRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -1967844409859893992L;

    private String name;
    private String username;
    private char[] password;
    private String email;

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
