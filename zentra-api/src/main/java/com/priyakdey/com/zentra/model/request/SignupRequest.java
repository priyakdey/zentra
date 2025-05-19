package com.priyakdey.com.zentra.model.request;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Priyak Dey
 */
public class SignupRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 795278877684944864L;

    private String email;
    // TODO: use byte[] for this
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
