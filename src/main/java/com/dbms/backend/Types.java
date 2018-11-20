package com.dbms.backend;

import com.dbms.data_types.Char;
import com.dbms.data_types.Varchar;

import java.util.HashMap;
import java.util.Map;

public class Types {

    private static Map<String, VAFunction<Object, ?>> types = new HashMap<>();
    //private static Map<String, BiFunction<String,Integer,?>> types = new HashMap<>();???
    //private static attributes???

    static {
        types.put("bool", args -> Boolean.parseBoolean((String) args[0]));
        types.put("byte", args -> Byte.parseByte((String) args[0]));
        types.put("short", args -> Short.parseShort((String) args[0]));
        types.put("int", args -> Integer.parseInt((String) args[0]));
        types.put("long", args -> Long.parseLong((String) args[0]));

        types.put("float", args -> Float.parseFloat((String) args[0]));
        types.put("double", args -> Double.parseDouble((String) args[0]));

        types.put("char", args -> Char.parseChar((String) args[0], (int) args[1]));
        types.put("varchar", args -> Varchar.parseVarchar((String) args[0], (int) args[1]));
    }

    public static boolean haveType(String name) {
        return types.containsKey(name);
    }

    public static Object cast(String value, TypeDescription typeDescription) {
        return
                value == null ?
                        null :
                        types.get(typeDescription.getName()).apply(value, typeDescription.getLength());
    }
}