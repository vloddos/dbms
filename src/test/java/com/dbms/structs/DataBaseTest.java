package com.dbms.structs;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;

public class DataBaseTest {

     private DataBase testDB = new DataBase("School");

    @Test
    public void testShouldReturnDatabaseName() {
        Assert.assertEquals("Films", new DataBase("Films").getName());
    }

    @Test(expected = Exception.class)
    public void testShouldThrowExceptionCauseTableDoesntExist() throws Exception {
        testDB.getTable("Student");
    }

    @Test(expected = Exception.class)
    public void testShouldThrowExceptionCauseTableAlreadyExists() throws Exception {
        var map = new LinkedHashMap<String, TypeDescription>();

        map.put("age", new TypeDescription("int"));
        map.put("field", new TypeDescription("bool"));

        testDB.createTable("Class", map);
        testDB.createTable("Class", map);
    }

    //FIXME(RoyalStorm): table exist, but access denied
    @Test
    public void testShouldReturnTableName() throws Exception {
        Assert.assertNotNull(testDB.getTable("Class"));
    }
}
