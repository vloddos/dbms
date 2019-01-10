package com.dbms.structs;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;

public class DataBaseTest extends TestBootstrap {

    private Database testDB = Databases.getInstance().getDatabase("School");

    public DataBaseTest() throws Exception {
    }

    @Test
    public void testShouldReturnDatabaseName() throws Exception {
        initAll();
        Assert.assertEquals("Films", new Database("Films").getName());
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
        Databases.getInstance().useDatabase("School");
        Assert.assertNotNull(Databases.getInstance().getCurrentDatabase().getTable("Class"));
    }
}