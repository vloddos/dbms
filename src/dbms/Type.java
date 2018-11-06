package dbms;

import java.io.Serializable;

public class Type implements Serializable {

    private static final long serialVersionUID = 2722592316258236135L;

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