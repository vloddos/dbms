package com.dbms.storage.serialization;

import com.dbms.structs.Types;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Random;

public class Serialization {

    private static Serialization instance;

    //private Kryo kryo = new Kryo();
    //private Set<Class<?>> kryoClasses = new HashSet<>();

    private Random random = new Random();
    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private DataOutputStream dos = new DataOutputStream(baos);

    private Serialization() {
    }

    public static Serialization getInstance() {
        if (instance == null)
            instance = new Serialization();

        return instance;
    }

    /*public synchronized Kryo getKryo() {
        var kryo = new Kryo();
        kryoClasses.forEach(kryo::register);
        return kryo;
    }

    public synchronized <T> void registerClassForKryo(Class<T> tClass) {
        kryo.register(tClass);
        kryoClasses.add(tClass);
    }

    public synchronized <E> byte[] getKryoBytes(E e) {
        var out = new Output(1024, -1);
        if (e instanceof KryoSerializable)
            ((KryoSerializable) e).write(kryo, out);
        else
            kryo.writeObject(out, e);
        return out.toBytes();
    }*/

    public synchronized <E> byte[] getDataSerializableBytes(E e) throws Exception {
        baos.reset();

        if (e instanceof DataSerializable)
            ((DataSerializable) e).write(dos);
        else
            Types.write(dos, e);

        return baos.toByteArray();
    }
}