package com.dbms;

import com.dbms.scripting.ScriptManager;

public class Test {

    public static void main(String[] args) throws Exception {
        /*var js = ScriptManager.scriptEngineManager.getEngineByName("js");
        System.out.println(js.eval("880055535-233"));*/

        /*var kryo = new Kryo();
        var out = new Output(1024, -1);

        var r = new Row();
        r.setRow(new ArrayList<>(Arrays.asList(new Char("f", 4), 234, true)));
        r.writeBlock(kryo, out);
        System.out.println(out.toBytes().length);

        var in = new Input(out.getBuffer());
        var s = new Row(
                new ArrayList<>(
                        Arrays.asList(
                                new Type("varchar", 4),
                                new Type("int"),
                                new Type("bool")
                        )
                )
        );
        s.read(kryo, in);
        s.row.forEach(e -> System.out.println(e.getClass()));
        System.out.println(s.row);*/
    }
}
