package com.dbms.global;

import com.esotericsoftware.kryo.Kryo;

import java.util.HashSet;
import java.util.Set;

public class Serialization {

    private static Serialization instance;
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
    public Kryo getKryo() {
        var kryo = new Kryo();
        kryoClasses.forEach(kryo::register);
        return kryo;
    }

    /**
     * Add a class to {@link Serialization#kryoClasses}.
     * If you register some class in the process, then you will most likely
     * need to update the kryo objects in the storage classes
     * (and possibly in other places where you get the cryo object from here) manually.
     *
     * @param tClass class to register
     */
    public <T> void registerClassForKryo(Class<T> tClass) {
        kryoClasses.add(tClass);
    }
    /*public static Kryo getKryo() {// FIXME: 02.12.2018 должно быть частью стораги
        var kryo = new Kryo();

        kryo.register(ArrayList.class);
        kryo.register(LinkedHashMap.class);
        kryo.register(HashMap.class);
        kryo.register(File.class);

        kryo.register(Char.class);
        kryo.register(Varchar.class);

        // FIXME: 02.12.2018 надо где то указывать какую логику регать в крио
        kryo.register(TypeDescription.class);
        kryo.register(Table.class);

        kryo.register(Block.class);

        return kryo;
    }*/
    //registerRecursive
    //register
}
