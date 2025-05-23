package com.priyakdey.com.zentra.security.core;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Priyak Dey
 */
public class SecureCharSequenceDeserializer extends JsonDeserializer<SecureCharSequence> {

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
