package com.dbms;

import com.dbms.data_types.Varchar;
import com.dbms.structures.Type;
import com.dbms.structures.Types;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

public class KryoTest {

    public static void main(String[] args) throws Exception {
        var baos = new ByteArrayOutputStream();
        var t = new Type("varchar", 10);
        var vc = new Varchar("mem", 10);
        System.out.println(vc);
        Types.write(new DataOutputStream(baos), vc);
        System.out.println(Arrays.toString(baos.toByteArray()));
        System.out.println(Types.read(new DataInputStream(new ByteArrayInputStream(baos.toByteArray())), t));
        System.out.println(new Varchar(new String(baos.toByteArray(), 0, 6, Charset.forName("utf16")), 10));
    }
}