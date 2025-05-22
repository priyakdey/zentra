package com.priyakdey.com.zentra.model.request;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.priyakdey.com.zentra.security.core.SecureCharSequence;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Priyak Dey
 */
public class AuthRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 795278877684944864L;

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

@Component
class SecureCharSequenceDeserializer extends JsonDeserializer<SecureCharSequence> {

    @Override
    public SecureCharSequence deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JacksonException {
        char[] characters = p.getTextCharacters();
        int offset = p.getTextOffset();
        int length = p.getTextLength();
        char[] slice = Arrays.copyOfRange(characters, offset, offset + length);
        return new SecureCharSequence(slice);
    }
}
