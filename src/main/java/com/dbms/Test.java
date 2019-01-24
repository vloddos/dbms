package com.dbms;

public class Test {

    public static void main(String[] args) throws Exception {
        //DBMSTest.main(new String[]{});
        //System.out.println(Integer.valueOf("23.4"));
        System.out.println(Integer.getInteger("23.4"));
        //"".

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
