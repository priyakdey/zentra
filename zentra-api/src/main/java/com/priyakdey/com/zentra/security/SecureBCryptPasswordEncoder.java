package com.priyakdey.com.zentra.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Priyak Dey
 */
public class SecureBCryptPasswordEncoder implements PasswordEncoder {

    private final BCryptVersion version;
    private final int strength;
    private final SecureRandom secureRandom;

    public SecureBCryptPasswordEncoder(BCryptVersion version, int strength,
                                       SecureRandom secureRandom) {
        this.version = version;
        this.strength = strength;
        this.secureRandom = secureRandom;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        Objects.requireNonNull(rawPassword);
        if (rawPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if (!(rawPassword instanceof SecureCharSequence(char[] data))) {
            throw new IllegalArgumentException("Expecting type SecureCharSequence");
        }

        String salt = BCrypt.gensalt(version.getVersion(), strength, secureRandom);

        byte[] buf = toUTF8Bytes(data);
        String hashpw = BCrypt.hashpw(buf, salt);

        Arrays.fill(buf, (byte) 0x0);
        Arrays.fill(data, (char) 0x0);

        return hashpw;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        Objects.requireNonNull(rawPassword);
        Objects.requireNonNull(encodedPassword);
        if (rawPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if (!(rawPassword instanceof SecureCharSequence(char[] data))) {
            throw new IllegalArgumentException("Expecting type SecureCharSequence");
        }

        byte[] buf = toUTF8Bytes(data);
        boolean isMatch = BCrypt.checkpw(buf, rawPassword.toString());

        Arrays.fill(buf, (byte) 0x0);
        Arrays.fill(data, (char) 0x0);

        return isMatch;
    }

    // since we do not want a text representation, I don't think encoding matters.
    private byte[] toUTF8Bytes(char[] chars) {
        CharBuffer charBuf = CharBuffer.wrap(chars);
        ByteBuffer bytebuf = StandardCharsets.UTF_8.encode(charBuf);
        return Arrays.copyOfRange(bytebuf.array(), bytebuf.position(), bytebuf.limit());
    }
}
