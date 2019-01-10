package com.dbms;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class A {

    ThreadLocal<Integer> i = ThreadLocal.withInitial(() -> {
        System.out.println("tli");
        return 228;
    });
}

public class Test {

    public static void main(String[] args) throws Exception {
        System.out.println(Arrays.toString(new File("testierherheh").listFiles(File::isFile)));



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
                                new TypeDescription("varchar", 4),
                                new TypeDescription("int"),
                                new TypeDescription("bool")
                        )
                )
        );
        s.read(kryo, in);
        s.row.forEach(e -> System.out.println(e.getClass()));
        System.out.println(s.row);*/
    }
}
