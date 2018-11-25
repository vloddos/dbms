package com.dbms.data_types;

public class Varchar {

    private char[] value;

    //for kryo
    private Varchar() {
    }

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