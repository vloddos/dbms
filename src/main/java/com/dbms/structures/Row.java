package com.dbms.structures;

import com.dbms.storage.serialization.DataSerializable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;

// TODO: 26.01.2019 добавить битовые флаги чтобы помечать null
public class Row implements DataSerializable, Cloneable {

    private static final byte DELETED = 0b1;

    public static final int SIZE_OF_SERVICE_DATA = 1;

    private byte FLAGS = 0;
    public ArrayList<Object> row = new ArrayList<>();
    private ArrayList<Type> types;

    private Row(byte FLAGS, ArrayList<Object> row) {
        this.FLAGS = FLAGS;
        this.row = row;
    }

    public Row() {
    }

    public Row(ArrayList<Type> types) {
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
        /*var sbb = new SingleBitBuffer(row.size());
        row.stream().map(e -> e == null ? 1 : 0).forEach(sbb::write);
        out.write(sbb.getBuffer());*/
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