package com.dbms.structs;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

public class TableTest {

    @Test(expected = RuntimeException.class)
    public void testShouldThrowExceptionCauseWrongType() {
        var map = new LinkedHashMap<String, TypeDescription>();

        map.put("age", new TypeDescription("int"));
        map.put("field", new TypeDescription("wrong"));

        new Table("School", "Student", map);
    }

    @Test
    public void testShouldReturnTableInStringFormat() {
        var map = new LinkedHashMap<String, TypeDescription>();

        map.put("first_name", new TypeDescription("varchar"));
        map.put("age", new TypeDescription("int"));

        Assert.assertEquals("create table Student(\n\tfirst_name varchar,\n\tage int\n)", new Table("School", "Student", map).toString());
    }

    @Test(expected = Exception.class)
    public void testShouldThrowExceptionCauseInsertIntoWrongField() throws Exception {
        DataBases.getInstance().useDataBase("School");

        var data = new HashMap();

        data.put("wrong", "23");

        DataBases.getInstance().getCurrentDataBase().getTable("Class").insert(data);
    }

    @Test
    public void testShouldInsertAndSelectValues() throws Exception {
        DataBases.getInstance().useDataBase("School");

        var fields = new LinkedHashMap<String, TypeDescription>();

        fields.put("first_name", new TypeDescription("varchar", 50));
        fields.put("last_name", new TypeDescription("varchar", 50));
        fields.put("age", new TypeDescription("int"));

        var data = new HashMap();

        data.put("first_name", "Mike");
        data.put("last_name", "Mike");
        data.put("age", "17");

        //DataBases.getInstance().getCurrentDataBase().getTable("student").insert(data);

        DataBases.getInstance().select(
                new Vector<>(Arrays.asList("age", "first_name", "last_name")),
                Integer.MAX_VALUE,
                0,
                "student",
                "age=17"
        ).printTable();
    }
}
