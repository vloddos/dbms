package com.dbms.structures;

import org.junit.Test;

public class DataBasesTest extends TestBootstrap {

    @Test(expected = Exception.class)
    public void testShouldThrowExceptionCauseTableDoesntExists() throws Exception {
        Databases.getInstance().getCurrentDatabase().getTable("City");
    }

    //FIXME(RoyalStorm): rename test
    @Test(expected = Exception.class)
    public void testShouldThrowExceptionDatabase() throws Exception {
        Databases.getInstance().createDatabase("School");
        Databases.getInstance().getDatabase("School");
    }

    /*@Test(expected = Exception.class)
    public void testShouldThrowExceptionCauseUseWrongDatabase() throws Exception {
        var map = new LinkedHashMap<String, Type>();

        map.put("first_name", new Type("varchar"));
        map.put("age", new Type("int"));

        Databases.getInstance().useDatabase("Test").createTable("student", map);
    }*/
}