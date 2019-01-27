package com.dbms.storage.serialization;

import com.dbms.structures.Types;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class Serialization {

    private static Serialization instance;

    //private Kryo kryo = new Kryo();
    //private Set<Class<?>> kryoClasses = new HashSet<>();

    private ThreadLocal<ByteArrayOutputStream> baosThreadLocal = ThreadLocal.withInitial(ByteArrayOutputStream::new);
    private ThreadLocal<DataOutputStream> dosThreadLocal = ThreadLocal.withInitial(
            () -> new DataOutputStream(baosThreadLocal.get())
    );

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

    public <E> byte[] getDataSerializableBytes(E e) throws Exception {
        var baos = baosThreadLocal.get();
        var dos = dosThreadLocal.get();

        baos.reset();

        if (e instanceof DataSerializable)
            ((DataSerializable) e).write(dos);
        else
            Types.write(dos, e);

        return baos.toByteArray();
    }
}