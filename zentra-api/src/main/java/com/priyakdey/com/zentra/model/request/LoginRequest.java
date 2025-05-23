package com.priyakdey.com.zentra.model.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.priyakdey.com.zentra.security.core.SecureCharSequence;
import com.priyakdey.com.zentra.security.core.SecureCharSequenceDeserializer;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Priyak Dey
 */
public class LoginRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -3323827231713679795L;

    private String email;

    @JsonDeserialize(using = SecureCharSequenceDeserializer.class)
    private SecureCharSequence password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SecureCharSequence getPassword() {
        return password;
    }

    public void setPassword(SecureCharSequence password) {
        this.password = password;
    }
}
