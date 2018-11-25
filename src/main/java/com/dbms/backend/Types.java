package com.dbms.backend;

import com.dbms.data_types.Char;
import com.dbms.data_types.Varchar;

import java.util.HashMap;
import java.util.Map;

public class Types {

    private static Map<String, VAFunction<Object, ?>> typeCastFunction = new HashMap<>();
    private static Map<String, Class> typeClassName = new HashMap<>();
    //private static Map<String, BiFunction<String,Integer,?>> types = new HashMap<>();???
    //private static attributes???

    static {
        typeCastFunction.put("bool", args -> Boolean.parseBoolean((String) args[0]));

        typeCastFunction.put("byte", args -> Byte.parseByte((String) args[0]));
        typeCastFunction.put("short", args -> Short.parseShort((String) args[0]));
        typeCastFunction.put("int", args -> Integer.parseInt((String) args[0]));
        typeCastFunction.put("long", args -> Long.parseLong((String) args[0]));

        typeCastFunction.put("float", args -> Float.parseFloat((String) args[0]));
        typeCastFunction.put("double", args -> Double.parseDouble((String) args[0]));

        typeCastFunction.put("char", args -> Char.parseChar((String) args[0], (int) args[1]));
        typeCastFunction.put("varchar", args -> Varchar.parseVarchar((String) args[0], (int) args[1]));
    }

    public static boolean haveType(String name) {
        return typeCastFunction.containsKey(name);
    }

    public static Object cast(String value, TypeDescription typeDescription) {
        return
                value == null ?
                        null :
                        typeCastFunction.get(typeDescription.getName()).apply(value, typeDescription.getLength());
    }
}