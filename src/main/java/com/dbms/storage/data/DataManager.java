package com.dbms.storage.data;

import com.dbms.storage.file_structs.FileStruct;

import java.io.File;

// FIXME: 18.12.2018 запилить метод обновления крио и проверить возможность обновить крио(многопоточность)
public class DataManager {

    private DataManager() {
    }

    private static DataManager instance;

    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();

        return instance;
    }

    public void initTableData(String databaseName, String tableName) throws Exception {
        if (!new File(FileStruct.getTableDataFullPath(databaseName, tableName)).createNewFile())
            throw new Exception(String.format("Cannot initialize table data for table '%s' in database '%s'", tableName, databaseName));
    }

    public void deleteTableData(String databaseName, String tableName) throws RuntimeException {
        if (!new File(FileStruct.getTableDataFullPath(databaseName, tableName)).delete())
            throw new RuntimeException(
                    String.format(
                            "Unsuccessful deletion of the table data '%s' from the database '%s'",
                            tableName,
                            databaseName
                    )
            );
    }
}
