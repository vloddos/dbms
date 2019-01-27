package com.dbms.structures;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

public class TableTest extends TestBootstrap {

    @Test(expected = RuntimeException.class)
    public void testShouldThrowExceptionCauseWrongType() {
        var map = new LinkedHashMap<String, Type>();

        map.put("age", new Type("int"));
        map.put("field", new Type("wrong"));

        new Table("School", "Student", map);
    }

    @Test
    public void testShouldReturnTableInStringFormat() {
        var map = new LinkedHashMap<String, Type>();

        map.put("first_name", new Type("varchar", 25));
        map.put("age", new Type("int"));

        Assert.assertEquals("create table Student(\n\tfirst_name varchar(25),\n\tage int\n)", new Table("School", "Student", map).toString());
    }

    @Test(expected = Exception.class)
    public void testShouldThrowExceptionCauseInsertIntoWrongField() throws Exception {
        Databases.getInstance().useDatabase("School");

        var data = new HashMap();

        data.put("wrong", "23");

        Databases.getInstance().getCurrentDatabase().getTable("Class").insert(data);
    }

    @Test
    public void testShouldInsertAndSelectValues() throws Exception {
        Databases.getInstance().useDatabase("School");

        var data = new HashMap<String, String>();

        data.put("field", "true");
        data.put("age", "17");

        Databases.getInstance().getCurrentDatabase().getTable("Class").insert(data);

        Databases.getInstance().getCurrentDatabase().getTable("Class").select(
                new Vector<>(Arrays.asList("age", "field")),
                Integer.MAX_VALUE,
                0,
                "age=17"
        ).print();
    }
}
