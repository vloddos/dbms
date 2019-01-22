package com.dbms.data_types;

public class Varchar extends Char {

    private int length;

    public static Char parseVarchar(String value, int length) {
        return new Varchar(value, length);
    }

    public Varchar(String value, int length) {
        super(value, length);
        this.length = value.length();
    }

    @Override
    public String toString() {
        return new String(chars, 0, length);
    }

    public String toCharString() {
        return super.toString();
    }
}