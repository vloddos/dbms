package com.dbms.storage;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Output;

import java.util.HashSet;
import java.util.Set;

public class Serialization {

    private static Serialization instance;

    private Kryo kryo = new Kryo();
    private Set<Class<?>> kryoClasses = new HashSet<>();

    private Serialization() {
    }

    public static Serialization getInstance() {
        if (instance == null)
            instance = new Serialization();

        return instance;
    }

    /**
     * @return a kryo object with all registered classes from {@link Serialization#kryoClasses}
     */
    public synchronized Kryo getKryo() {
        var kryo = new Kryo();
        kryoClasses.forEach(kryo::register);
        return kryo;
    }

    /**
     * Add a class to {@link Serialization#kryoClasses}.
     * If you register some class in the process, then you will most likely
     * need to update the kryo objects in the storage classes
     * (and possibly in other places where you get the kryo object from here) manually.
     *
     * @param tClass class to register
     */
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
    }
}