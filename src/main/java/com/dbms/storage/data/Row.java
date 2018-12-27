package com.dbms.storage.data;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.util.ArrayList;

//@DefaultSerializer(Record.RecordSerializer.class)
public class Record implements KryoSerializable, Cloneable {

    private static final byte DELETED = 0b1;

    private byte FLAGS = 0;
    private ArrayList<Object> record = new ArrayList<>();
    private ArrayList<Class<Object>> classes;

    private Record(byte FLAGS, ArrayList<Object> record) {
        this.FLAGS = FLAGS;
        this.record = record;
    }

    public Record(ArrayList<Object> record) {
        this.record = record;
    }

    public Record(ArrayList<Class<Object>> classes) {
        this.classes = classes;
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
        record.forEach(
                e -> {
                    var ec = e.getClass();
                    if (ec.equals(Boolean.class))
                        output.writeBoolean((boolean) e);
                    else if (ec.equals(Byte.class))
                        output.writeByte((byte) e);
                    else if (ec.equals(Short.class))
                        output.writeShort((short) e);
                    else if (ec.equals(Integer.class))
                        output.writeInt((int) e);
                    else if (ec.equals(Long.class))
                        output.writeLong((long) e);
                    else if (ec.equals(Float.class))
                        output.writeFloat((float) e);
                    else if (ec.equals(Double.class))
                        output.writeDouble((double) e);
                }
        );
    }

    @Override
    public void read(Kryo kryo, Input input) {
        record.clear();
        FLAGS = input.readByte();
        classes.forEach(
                c -> {
                    if (c.equals(Boolean.class))
                        record.add(input.readBoolean());
                    else if (c.equals(Byte.class))
                        record.add(input.readByte());
                    else if (c.equals(Short.class))
                        record.add(input.readShort());
                    else if (c.equals(Integer.class))
                        record.add(input.readInt());
                    else if (c.equals(Long.class))
                        record.add(input.readLong());
                    else if (c.equals(Float.class))
                        record.add(input.readFloat());
                    else if (c.equals(Double.class))
                        record.add(input.readDouble());
                }
        );
    }

    @Override
    public Record clone() {
        return new Record(FLAGS, (ArrayList<Object>) record.clone());
    }

    /*public static class RecordSerializer extends Serializer<Record> {

        @Override
        public void write(Kryo kryo, Output output, Record record) {
            output.writeLong(record.FLAGS);
            record.record.forEach(
                    e -> {
                        var ec = e.getClass();
                        if (ec.equals(Boolean.class))
                            output.writeBoolean((boolean) e);
                        else if (ec.equals(Byte.class))
                            output.writeByte((byte) e);
                        else if (ec.equals(Short.class))
                            output.writeShort((short) e);
                        else if (ec.equals(Integer.class))
                            output.writeInt((int) e);
                        else if (ec.equals(Long.class))
                            output.writeLong((long) e);
                        else if (ec.equals(Float.class))
                            output.writeFloat((float) e);
                        else if (ec.equals(Double.class))
                            output.writeDouble((double) e);
                        else
                            System.out.println("4toto ne tak");//debug
                    }
            );
        }

        @Override
        public Record read(Kryo kryo, Input input, Class<? extends Record> type) {
            var record = new Record();
            record.FLAGS = input.readByte();
            //input.

            return record;
        }
    }*/
}
