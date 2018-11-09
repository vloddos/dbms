package com.dbms.backend;

import java.io.Serializable;

public class Type implements Serializable {

    private static final long serialVersionUID = -3306694402030933798L;

    public String name;
    //public Integer length;

    public Type(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}