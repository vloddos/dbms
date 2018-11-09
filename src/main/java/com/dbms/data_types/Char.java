package com.dbms.data_types;

import java.io.Serializable;
import java.util.Arrays;

public class Char implements Serializable {

    private static final long serialVersionUID = 1218065345404311779L;

    private char[] value;

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