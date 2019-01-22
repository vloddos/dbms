package com.dbms.storage.data;

import com.dbms.storage.serialization.DataSerializable;
import com.dbms.structs.TypeDescription;
import com.dbms.structs.Types;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;

public class Row implements DataSerializable, Cloneable {

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
    public void write(DataOutputStream out) throws Exception {
        out.writeByte(FLAGS);
        for (var e : row)
            Types.write(out, e);
    }

    @Override
    public void read(DataInputStream in) throws Exception {
        row.clear();
        FLAGS = in.readByte();
        for (var t : types)
            row.add(Types.read(in, t));
    }

    @Override
    public Row clone() {
        return new Row(FLAGS, (ArrayList<Object>) row.clone());
    }
}