package com.dbms.storage;

import com.dbms.global.Global;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class RowIterator implements Iterator<ArrayList> {

    private Kryo kryo = Global.getKryo();
    private Input input;

    public RowIterator(InputStream inputStream) {
        input = new Input(inputStream);
    }

    @Override
    public boolean hasNext() {
        if (input != null)
            if (input.end()) {
                input.close();
                input = null;
            } else
                return true;
        return false;
    }

    @Override
    public ArrayList next() {
        return hasNext() ? kryo.readObject(input, ArrayList.class) : null;
    }
}
