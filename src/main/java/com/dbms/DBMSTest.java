package com.dbms;

import com.dbms.storage.Serialization;
import com.dbms.storage.blocks.BlockManager;
import com.dbms.storage.file_structs.BlockExtendedFileStruct;
import com.dbms.structs.Database;
import com.dbms.structs.Databases;
import com.dbms.structs.Table;
import com.dbms.structs.TypeDescription;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class DBMSTest {

    public static void main(String[] args) throws Exception {
        Serialization.getInstance().registerClassForKryo(HashMap.class);
        Serialization.getInstance().registerClassForKryo(LinkedHashMap.class);

        Serialization.getInstance().registerClassForKryo(TypeDescription.class);
        Serialization.getInstance().registerClassForKryo(Table.class);
        Serialization.getInstance().registerClassForKryo(Database.class);

        BlockManager.init();
        BlockExtendedFileStruct.init();
        Databases.getInstance().fullDatabasesFullTablesLoad();

        /*var map = new HashMap<String, Pair<String, Integer>>();
        map.put("f1", new Pair<>("row", 0));
        map.put("f2", new Pair<>("row", 1));
        map.put("f3", new Pair<>("row", 2));
        map.put("f4", new Pair<>("row", 3));
        var e = new Expression("@a:=f2 or f1=2*f2-f3 and f3>=f2/f1 or f4-f1=f2+f3", map);

        System.out.println(e.getValue());
        System.out.println(e.getValueForEval());*/

        /*var fn = new Vector<>(Arrays.asList("f1", "longfieldname", "f2"));
        var rows = new Vector<>(
                Arrays.asList(
                        new Vector(Arrays.asList(1, "2873rfwegeg42", false)),
                        new Vector(Arrays.asList(1488, "wetfgw", true)),
                        new Vector(Arrays.asList(666, "pqwoiefbwvwbvwiubwbw", false)),
                        new Vector(Arrays.asList(228, "7372h", false)),
                        new Vector(Arrays.asList(-113, "913hr gyqeu fwsns fk ", true)),
                        new Vector(Arrays.asList(-34396436, "rfw", true))
                )
        );
        new TextTable(new DefaultTableModel(rows, fn)).printTable();*/


        //MetaDataManager.getInstance().init();
        //Databases.getInstance().fullDatabasesFullTablesLoad();

        //Databases.getInstance().dropDatabase("tdb");
        //Databases.getInstance().createDatabase("tdb");

        /*var map = new LinkedHashMap<String, TypeDescription>();
        map.put("name", new TypeDescription("varchar", 10));
        map.put("age", new TypeDescription("int"));
        map.put("field", new TypeDescription("bool"));
        Databases.getInstance().useDatabase("School").createTable("student", map);*/

        System.out.println(Databases.getInstance().getCurrentDatabase().getTable("student"));


        //Databases.getInstance().getCurrentDatabase().dropTable("student");
        //Databases.getInstance().getCurrentDatabase().getTable("student");


        /*var map = new HashMap();
        map.put("name", "mem");
        map.put("age", "1488");
        map.put("field", "true");
        Databases.getInstance().getCurrentDatabase().getTable("student").insert(map);*/

        /*var set = new HashMap<String, String>();
        set.put("age", "1488");
        set.put("name", "'adolf'");
        Databases.getInstance().getCurrentDatabase().getTable("student").update(set, null);*/

        /*Databases.getInstance().getCurrentDatabase().getTable("student").select(
                new Vector<>(Arrays.asList("name", "age", "field")),
                Integer.MAX_VALUE,
                0,
                null
        ).print();*/

        //Databases.getCurrentDatabase().getTable("student").delete("field=false");


        /*var sem = new ScriptEngineManager();
        var se = sem.getEngineByName("js");
        se.eval(
                "var Boolean=Java.type('java.lang.Boolean');" +
                        "var Byte=Java.type('java.lang.Byte');" +
                        "var Short=Java.type('java.lang.Short');" +
                        "var Integer=Java.type('java.lang.Integer');" +
                        "var Long=Java.type('java.lang.Long');" +
                        "var Float=Java.type('java.lang.Float');" +
                        "var Double=Java.type('java.lang.Double');" +
                        "var Char=Java.type('com.dbms.data_types.Char');" +
                        "var Varchar=Java.type('com.dbms.data_types.Varchar');");
        var p = Pattern.compile("[a-zA-Z_]\\w+");
        var s = "f1=f2*f3-f1+f4,f5=f6 and not f7 or f8";*/

        //new ObjectInputStream(new FileInputStream())



        /*Kryo kryo = new Kryo();
        Output output = new Output(new FileOutputStream("test2"));
        //kryo.writeObject(output, -234567);
        kryo.writeObject(output, 234);
        kryo.writeObject(output, 152);
        kryo.writeObject(output, 589);
        output.close();

        Output tmp = new Output(1024);
        kryo.writeObject(tmp, -234567);
        var rac = new RandomAccessFile("test2", "rw");
        rac.seek(0);
        rac.setLength(rac.length() + tmp.toBytes().length);
        rac.writeBlock(tmp.toBytes());
        rac.close();
        System.out.println(Arrays.toString(tmp.toBytes()));


        Input input = new Input(new FileInputStream("test2"));
        for (int i = 0; i < 4; ++i)
            System.out.println(kryo.readObject(input, int.class));

        input.close();*/


        /*Integer a = 45;
        Double b = 892.34;
        Boolean c = true;
        Object oa = a, ob = b, oc = c;
        se.put("oa", oa);
        se.put("ob", ob);
        se.put("oc", oc);
        var r = se.eval("oa + ob;");
        System.out.println(r);
        System.out.println(r.getClass());*/

        /*System.out.println(Integer.class.getName());
        System.out.println(Integer.class.getSimpleName());
        System.out.println(Char.class.getName());
        System.out.println(Char.class.getSimpleName());*/

        /*var tmp = p.matcher(s).replaceAll(
                mr -> {
                    var g = mr.group();
                    switch (g.toLowerCase()) {
                        case "and":
                            return "&&";
                        case "or":
                            return "||";
                        case "not":
                            return "!";
                        default:
                            return "t1.getElement(" + g + ")";
                    }
                }
        );
        System.out.println(tmp);*/

        //se.eval("out.println(new C1(56).getI());");
        //se.eval("out.println(new C1(56).getI());");
        //se.eval("System.out.println(245);");
    }
}
