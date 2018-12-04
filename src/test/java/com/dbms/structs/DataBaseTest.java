package com.dbms.structs;

import com.dbms.storage.StorageEngine;
import org.junit.Test;

public class DataBaseTest {

    @Test(expected = Exception.class)
    public void testShouldThrowExceptionCauseDatabaseDoesntExists() throws Exception {
        DataBases.getInstance().getCurrentDataBase().getTable("City");
    }

    @Test(expected = Exception.class)
    public void testShouldThrowExceptionDatabase() throws Exception {
        StorageEngine.getInstance().init();

        DataBases.getInstance().fullLoad();
        DataBases.getInstance().createDataBase("School");
        DataBases.getInstance().getDataBase("School");
    }
}
