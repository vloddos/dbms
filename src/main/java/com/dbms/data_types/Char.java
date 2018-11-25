package com.dbms.data_types;

import java.util.Arrays;

public class Char {

    private char[] value;

    //for kryo
    private Char() {
    }

    public static Char parseChar(String value, int length) {
        return new Char(value, length);
    }

    public Char(String value, int length) throws RuntimeException {
        if (value.length() > length)
            throw new RuntimeException(String.format("The length of '%s' more than %s", value, length));

        this.value = Arrays.copyOf(value.toCharArray(), length);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}