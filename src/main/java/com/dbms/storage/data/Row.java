package com.dbms.storage.data;

import com.dbms.structs.TypeDescription;
import com.dbms.structs.Types;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.util.ArrayList;

public class Row implements KryoSerializable, Cloneable {

    private static final byte DELETED = 0b1;

    public static final int SIZE_OF_SERVICE_DATA = 1;

    private byte FLAGS = 0;
    public ArrayList<Object> row = new ArrayList<>();
    private ArrayList<TypeDescription> types;

    private Row(byte FLAGS, ArrayList<Object> row) {
        this.FLAGS = FLAGS;
        this.row = row;
    }

    public Row() {
    }

    public Row(ArrayList<TypeDescription> types) {
        this.types = types;
    }

    public boolean isDeleted() {
        return (FLAGS & DELETED) == DELETED;
    }

    public void makeDeleted() {
        FLAGS |= DELETED;
    }

    @Override
    public void write(Kryo kryo, Output output) {
        output.writeByte(FLAGS);
        row.forEach(e -> Types.write(e.getClass(), output, e));
    }

    @Override
    public void read(Kryo kryo, Input input) {
        row.clear();
        FLAGS = input.readByte();
        types.forEach(t -> row.add(Types.read(t, input)));
    }

    @Override
    public Row clone() {
        return new Row(FLAGS, (ArrayList<Object>) row.clone());
    }
}
