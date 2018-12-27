package com.dbms.storage.data;

import com.dbms.global.Global;
import com.dbms.storage.RowIterator;
import com.dbms.storage.file_structs.FileStruct;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class DataManager {

    private DataManager() {
    }

    private static DataManager instance;

    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();

        return instance;
    }

    private Kryo kryo = Global.getKryo();

    public void initTableData(String databaseName, String tableName) throws Exception {
        if (!new File(FileStruct.getTableDataPath(databaseName, tableName)).mkdir())
            throw new Exception(String.format("Cannot initialize table data for table '%s' in database '%s'", tableName, databaseName));
    }

    public void appendRow(String databaseName, String tableName, ArrayList<?> row) throws Exception {
        try (var out = new Output(new FileOutputStream(FileStruct.getTableDataPath(databaseName, tableName), true))) {
            kryo.writeObject(out, row);
        }
    }

    // FIXME: 30.11.2018 close stream if fails???
    public RowIterator getRowIterator(String databaseName, String tableName) throws Exception {
        return new RowIterator(new FileInputStream(FileStruct.getTableDataPath(databaseName, tableName)));
    }

    public String createTempTableData(String databaseName, String tableName) throws Exception {
        var tempTableName = tableName + FileStruct.getTmpFileExtension();
        initTableData(databaseName, tempTableName);
        return tempTableName;
    }

    public void replaceTableDataToTemp(String databaseName, String tableName) throws Exception {
        var p = Paths.get(FileStruct.getTableDataPath(databaseName, tableName + FileStruct.getTmpFileExtension()));
        Files.move(p, p.resolveSibling(tableName), StandardCopyOption.REPLACE_EXISTING);
    }
}
