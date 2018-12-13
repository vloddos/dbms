package com.dbms.structs;

import com.dbms.storage.StorageEngine;
import org.junit.Test;

import java.util.LinkedHashMap;

public class DataBasesTest {

    @Test(expected = Exception.class)
    public void testShouldThrowExceptionCauseDatabaseDoesntExists() throws Exception {
        DataBases.getInstance().getCurrentDataBase().getTable("City");
    }

    //FIXME(RoyalStorm): rename test
    @Test(expected = Exception.class)
    public void testShouldThrowExceptionDatabase() throws Exception {
        StorageEngine.getInstance().init();

        DataBases.getInstance().fullLoad();
        DataBases.getInstance().createDataBase("School");
        DataBases.getInstance().getDataBase("School");
    }

    @Test(expected = Exception.class)
    public void testShouldThrowExceptionCauseUseWrongDatabase() throws Exception {
        var map = new LinkedHashMap<String, TypeDescription>();

        map.put("first_name", new TypeDescription("varchar"));
        map.put("age", new TypeDescription("int"));

        DataBases.getInstance().useDataBase("Test").createTable("student", map);
    }

    @Test(expected = Exception.class)
    public void testShouldThrowExceptionCauseDatabaseNotSelected() throws Exception {
        DataBases.getInstance().getCurrentDataBase();
    }
}
