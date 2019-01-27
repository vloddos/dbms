package com.dbms.structures;

import com.dbms.classes.lambdas_throwing.BiConsumerThrowing;
import com.dbms.classes.lambdas_throwing.BiFunctionThrowing;
import com.dbms.data_types.Char;
import com.dbms.data_types.Varchar;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Types {

    private static BiMap<String, Class<?>> typeClasses = HashBiMap.create();
    private static Map<String, Integer> typeSizes = new HashMap<>();
    private static Map<String, BiFunction<String, Integer, ?>> typeCastFunction = new HashMap<>();
    private static Map<String, Object> defaultValues = new HashMap<>();
    private static Map<String, BiConsumerThrowing<DataOutputStream, Object>> typeWriteFunction = new HashMap<>();
    private static Map<String, BiFunctionThrowing<DataInputStream, Integer, ?>> typeReadFunction = new HashMap<>();

    static {
        typeClasses.put("bool", Boolean.class);

        typeClasses.put("byte", Byte.class);
        typeClasses.put("short", Short.class);
        typeClasses.put("int", Integer.class);
        typeClasses.put("long", Long.class);

        typeClasses.put("float", Float.class);
        typeClasses.put("double", Double.class);

        typeClasses.put("char", Char.class);
        typeClasses.put("varchar", Varchar.class);
    }

    static {
        typeSizes.put("bool", 1);

        typeSizes.put("byte", 1);
        typeSizes.put("short", 2);
        typeSizes.put("int", 4);
        typeSizes.put("long", 8);

        typeSizes.put("float", 4);
        typeSizes.put("double", 8);

        typeSizes.put("char", 2);//1 symbol
        typeSizes.put("varchar", 2);//1 symbol
    }

    static {
        typeCastFunction.put("bool", (s, l) -> Boolean.parseBoolean(s));

        Function<String, String> intSubstring = (s) -> {//e?
            var l = s.indexOf(".");
            if (l == -1)
                l = s.length();
            return s.substring(0, l);
        };

        typeCastFunction.put("byte", (s, l) -> Byte.parseByte(intSubstring.apply(s)));
        typeCastFunction.put("short", (s, l) -> Short.parseShort(intSubstring.apply(s)));
        typeCastFunction.put("int", (s, l) -> Integer.parseInt(intSubstring.apply(s)));
        typeCastFunction.put("long", (s, l) -> Long.parseLong(intSubstring.apply(s)));

        typeCastFunction.put("float", (s, l) -> Float.parseFloat(s));
        typeCastFunction.put("double", (s, l) -> Double.parseDouble(s));

        typeCastFunction.put("char", Char::parseChar);
        typeCastFunction.put("varchar", Varchar::parseVarchar);
    }

    static {
        defaultValues.put("bool", false);

        defaultValues.put("byte", 0);
        defaultValues.put("short", 0);
        defaultValues.put("int", 0);
        defaultValues.put("long", 0);

        defaultValues.put("float", 0);
        defaultValues.put("double", 0);
    }

    static {
        typeWriteFunction.put("bool", (out, b) -> out.writeBoolean((boolean) b));

        typeWriteFunction.put("byte", (out, b) -> out.writeByte((byte) b));
        typeWriteFunction.put("short", (out, s) -> out.writeShort((short) s));
        typeWriteFunction.put("int", (out, i) -> out.writeInt((int) i));
        typeWriteFunction.put("long", (out, l) -> out.writeLong((long) l));

        typeWriteFunction.put("float", (out, f) -> out.writeFloat((float) f));
        typeWriteFunction.put("double", (out, d) -> out.writeDouble((double) d));

        typeWriteFunction.put("char", (out, c) -> out.writeChars((c.toString())));
        typeWriteFunction.put("varchar", (out, vc) -> out.writeChars(((Varchar) vc).toCharString()));
    }

    static {
        typeReadFunction.put("bool", (in, length) -> in.readBoolean());

        typeReadFunction.put("byte", (in, length) -> in.readByte());
        typeReadFunction.put("short", (in, length) -> in.readShort());
        typeReadFunction.put("int", (in, length) -> in.readInt());
        typeReadFunction.put("long", (in, length) -> in.readLong());

        typeReadFunction.put("float", (in, length) -> in.readFloat());
        typeReadFunction.put("double", (in, length) -> in.readDouble());

        typeReadFunction.put("char", (in, length) -> {
            var tmp = new byte[length * getSize("char")];
            in.read(tmp);
            return new Char(new String(tmp, Charset.forName("utf16")), length);
        });
        typeReadFunction.put("varchar", (in, length) -> {
            var tmp = new byte[length * getSize("varchar")];
            in.read(tmp);
            int i;
            for (i = 1; i < tmp.length && !(tmp[i] == 0 && tmp[i - 1] == 0); ++i) ;//???
            return new Varchar(new String(tmp, 0, i / 2 * 2, Charset.forName("utf16")), length);
        });
    }

    public static boolean haveType(String name) {
        return typeClasses.containsKey(name);
    }

    public static int getSize(String name) {
        return typeSizes.get(name);
    }

    public static Object cast(String value, Type type) {
        return
                value == null ?
                        null :
                        typeCastFunction.get(type.getName()).apply(value, type.getLength());
    }

    public static Object getDefaultValue(String typeName) {
        return defaultValues.get(typeName);
    }

    /*public static Object getDefaultValue(Type type){

    }*/

    public static <E> void write(DataOutputStream out, E e) throws Exception {
        typeWriteFunction
                .get(typeClasses.inverse().get(e.getClass()))
                .accept(out, e);
    }

    public static Object read(DataInputStream in, Type type) throws Exception {
        return
                typeReadFunction
                        .get(type.getName())
                        .apply(in, type.getLength());
    }
}