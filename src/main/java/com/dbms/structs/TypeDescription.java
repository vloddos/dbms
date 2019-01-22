package com.dbms.structs;

import java.io.Serializable;

public class TypeDescription implements Serializable {

    private static final long serialVersionUID = 6270410567624650794L;

    private String name;
    private int length = -1;

    //for kryo
    private TypeDescription() {
    }

    public TypeDescription(String name) {
        this.name = name;
    }

    public TypeDescription(String name, int length) {
        this(name);
        setLength(length);
    }

    public TypeDescription(String name, String length) {
        this(name);
        setLength(length);
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setLength(String length) {
        this.length = Integer.parseInt(length);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return name + (length == -1 ? "" : "(" + length + ")");
    }
}