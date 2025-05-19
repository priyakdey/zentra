package com.priyakdey.com.zentra.security;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Priyak Dey
 */
public record SecureCharSequence(char[] data) implements CharSequence {

    @Override
    public int length() {
        return data.length;
    }

    @Override
    public char charAt(int index) {
        return data[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        Objects.checkFromToIndex(start, end, data.length);
        return new SecureCharSequence(Arrays.copyOfRange(data, start, end));
    }

    @Override
    public boolean isEmpty() {
        return data.length == 0;
    }

    @Override
    public String toString() {
        return "";
    }
}
