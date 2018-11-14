package com.dbms.backend;

import java.io.Serializable;

public class TypeDescription implements Serializable {

    private static final long serialVersionUID = 2722592316258236135L;

    private String name;
    private Integer length;

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setLength(String length) {
        this.length = Integer.valueOf(length);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getLength() {
        return length;
    }

    public TypeDescription(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + (length == null ? "" : "(" + length + ")");
    }
}