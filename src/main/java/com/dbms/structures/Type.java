package com.dbms.structures;

import java.io.Serializable;

// TODO: 24.01.2019 инкапсулировать все типы в этот класс или в другой чтобы не дублировать код или как то поменять Types(для сериализации как минимум)
public class Type implements Serializable {

    private static final long serialVersionUID = 6270410567624650794L;

    private String name;
    private int length = -1;

    //for kryo
    private Type() {
    }

    public Type(String name) {
        this.name = name;
    }

    public Type(String name, int length) {
        this(name);
        setLength(length);
    }

    public Type(String name, String length) {
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