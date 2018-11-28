package com.dbms.global;

import com.dbms.data_types.Char;
import com.dbms.data_types.Varchar;
import com.dbms.structs.Table;
import com.dbms.structs.TypeDescription;
import com.esotericsoftware.kryo.Kryo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Global {

    //1 если возвращать общую регистрацию то возможно будет трабл из-за 1 объекта сериализатора
    //2 хуй пойми вообще как получить мэп классов и регистраций
    //3 но возможно когда нибудь я это отрефакторю
    public static Kryo getKryo() {
        var kryo = new Kryo();

        kryo.register(ArrayList.class);
        kryo.register(LinkedHashMap.class);
        kryo.register(HashMap.class);

        kryo.register(Char.class);
        kryo.register(Varchar.class);

        kryo.register(TypeDescription.class);

        kryo.register(Table.class);

        return kryo;
    }
}
