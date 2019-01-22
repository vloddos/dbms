package com.dbms.structs;

import com.dbms.data_types.Char;
import com.dbms.data_types.Varchar;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Types {

    private static BiMap<String, Class<?>> typeClasses = HashBiMap.create();
    private static Map<String, Integer> typeSizes = new HashMap<>();
    private static Map<String, VAFunction<Object, ?>> typeCastFunction = new HashMap<>();
    private static Map<String, Object> defaultValues = new HashMap<>();
    //private static Map<String, BiConsumer<Output, Object>> typeWriteFunction = new HashMap<>();
    //private static Map<String, BiFunction<Input, Integer, ?>> typeReadFunction = new HashMap<>();
    private static Map<String, BiConsumerThrowing<DataOutputStream, Object>> typeWriteFunction = new HashMap<>();
    private static Map<String, BiFunctionThrowing<DataInputStream, Integer, ?>> typeReadFunction = new HashMap<>();

    //private static Map<String, BiFunction<String,Integer,?>> types = new HashMap<>();???
    //private static attributes???
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

    static {
        defaultValues.put("bool", false);

        defaultValues.put("byte", 0);
        defaultValues.put("short", 0);
        defaultValues.put("int", 0);
        defaultValues.put("long", 0);

        defaultValues.put("float", 0);
        defaultValues.put("double", 0);
    }

    /*static {
        typeWriteFunction.put("bool", (out, b) -> out.writeBoolean((boolean) b));

        typeWriteFunction.put("byte", (out, b) -> out.writeByte((byte) b));
        typeWriteFunction.put("short", (out, s) -> out.writeShort((short) s));
        typeWriteFunction.put("int", (out, i) -> out.writeInt((int) i));
        typeWriteFunction.put("long", (out, l) -> out.writeLong((long) l));

        typeWriteFunction.put("float", (out, f) -> out.writeFloat((float) f));
        typeWriteFunction.put("double", (out, d) -> out.writeDouble((double) d));

        typeWriteFunction.put("char", (out, c) -> {
            var cs = ((Char) c).getChars();
            out.writeChars(cs, 0, cs.length);
        });
        typeWriteFunction.put("varchar", (out, vc) -> {
            var cs = ((Varchar) vc).getChars();
            out.writeChars(cs, 0, cs.length);
        });
    }

    static {
        typeReadFunction.put("bool", (in, length) -> in.readBoolean());

        typeReadFunction.put("byte", (in, length) -> in.readByte());
        typeReadFunction.put("short", (in, length) -> in.readShort());
        typeReadFunction.put("int", (in, length) -> in.readInt());
        typeReadFunction.put("long", (in, length) -> in.readLong());

        typeReadFunction.put("float", (in, length) -> in.readFloat());
        typeReadFunction.put("double", (in, length) -> in.readDouble());

        typeReadFunction.put("char", (in, length) -> new Char(new String(in.readChars(length)), length));
        typeReadFunction.put("varchar", (in, length) -> {
            var cs = in.readChars(length);
            int i;
            for (i = 0; i < cs.length && cs[i] != 0; ++i) ;//???
            return new Varchar(new String(cs, 0, i), length);
        });
    }*/

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

    public static Object cast(String value, TypeDescription typeDescription) {
        return
                value == null ?
                        null :
                        typeCastFunction.get(typeDescription.getName()).apply(value, typeDescription.getLength());
    }

    public static Object getDefaultValue(String typeName) {
        return defaultValues.get(typeName);
    }

    public static <E> void write(DataOutputStream out, E e) throws Exception {
        typeWriteFunction
                .get(typeClasses.inverse().get(e.getClass()))
                .accept(out, e);
    }

    public static Object read(DataInputStream in, TypeDescription type) throws Exception {
        return
                typeReadFunction
                        .get(type.getName())
                        .apply(in, type.getLength());
    }
}