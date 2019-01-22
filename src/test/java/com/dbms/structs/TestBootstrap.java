package com.dbms.structs;

import com.dbms.storage.file_structs.BlockExtendedFileStruct;
import org.junit.BeforeClass;

public abstract class TestBootstrap {

    @BeforeClass//???
    public static void initAll() throws Exception {
        BlockExtendedFileStruct.init();
        Databases.getInstance().fullDatabasesFullTablesLoad();
    }
}
