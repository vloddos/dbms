package com.dbms.structs;

import com.dbms.storage.Serialization;
import com.dbms.storage.blocks.BlockManager;
import com.dbms.storage.file_structs.BlockExtendedFileStruct;
import org.junit.BeforeClass;

import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class TestBootstrap {

    @BeforeClass//???
    public static void initAll() throws Exception {
        Serialization.getInstance().registerClassForKryo(HashMap.class);
        Serialization.getInstance().registerClassForKryo(LinkedHashMap.class);

        Serialization.getInstance().registerClassForKryo(TypeDescription.class);
        Serialization.getInstance().registerClassForKryo(Table.class);
        Serialization.getInstance().registerClassForKryo(Database.class);

        BlockManager.init();
        BlockExtendedFileStruct.init();
        Databases.getInstance().fullDatabasesFullTablesLoad();
    }
}
