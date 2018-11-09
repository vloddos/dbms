package com.dbms.data_types;

import java.io.Serializable;

public class Varchar implements Serializable {

    private static final long serialVersionUID = -807614549893159685L;

    private char[] value;

    public static Varchar parseVarchar(String value, int length) {
        return new Varchar(value, length);
    }

    public Varchar(String value, int length) throws RuntimeException {
        if (value.length() > length)
            throw new RuntimeException(String.format("The length of '%s' more than %s", value, length));

        this.value = value.toCharArray();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}