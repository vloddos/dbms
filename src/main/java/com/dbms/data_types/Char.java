package com.dbms.data_types;

import java.util.Arrays;

public class Char {//какие символы должны заполнять оставшееся место???

    protected char[] chars;

    public static Char parseChar(String value, int length) {
        return new Char(value, length);
    }

    public Char(String value, int length) throws RuntimeException {
        if (value.length() > length)
            throw new RuntimeException(String.format("The length of '%s' more than %s", value, length));

        chars = Arrays.copyOf(value.toCharArray(), length);
    }

    public char[] getChars() {
        return chars;
    }

    @Override
    public String toString() {
        return new String(chars);
    }
}