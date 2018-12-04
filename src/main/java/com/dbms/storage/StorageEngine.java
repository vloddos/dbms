package com.dbms.storage;

import com.dbms.global.Global;
import com.dbms.structs.DataBase;
import com.dbms.structs.Table;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StorageEngine {

    private StorageEngine() {
    }

    private static StorageEngine instance;

    public static StorageEngine getInstance() {
        if (instance == null)
            instance = new StorageEngine();

        return instance;
    }

    private String dataBaseRootDirectory = "db";
    private String tableHeaders = getFilePath("meta_data", "table_headers");
    private String tableData = getFilePath("data", "table_data");

    private String[] dbDirectories = {//full paths of service folders
            tableHeaders,
            tableData
    };

    private String tmpFileExtension = ".tmp";

    private Kryo kryo = Global.getKryo();

    public void init() throws Exception {
        var dbrd = new File(dataBaseRootDirectory);
        if (!dbrd.isDirectory())
            if (!dbrd.mkdir())
                throw new Exception("Cannot create database root directory");
    }

    private String getFilePath(String first, String... more) {
        return Paths.get(first, more).toString();
    }

    private String db_path(String name) {
        return getFilePath(dataBaseRootDirectory, name);
    }

    public DataBase readDataBase(File dataBaseDirectory) throws Exception {
        var dataBaseName = dataBaseDirectory.getName();

        if (!dataBaseDirectory.isDirectory())
            throw new Exception(String.format("The '%s' database does not exist", dataBaseName));

        //read tables
        var tables = new HashMap<String, Table>();
        for (var f : new File(getFilePath(dataBaseDirectory.toString(), tableHeaders)).listFiles(File::isFile))
            tables.put(f.getName(), readTable(f));

        return new DataBase(dataBaseName, tables);
    }

    public DataBase readDataBase(String name) throws Exception {
        return readDataBase(new File(db_path(name)));
    }

    public Map<String, DataBase> readAllDataBases() throws Exception {
        var dataBases = new HashMap<String, DataBase>();

        for (var f : new File(dataBaseRootDirectory).listFiles(File::isDirectory))
            dataBases.put(f.getName(), readDataBase(f));//must not throw exception

        return dataBases;
    }

    public void initDataBase(String name) throws Exception {
        var db_path = db_path(name);

        if (new File(db_path).exists())
            throw new Exception(String.format("The database '%s' already exists", name));

        for (var d : dbDirectories) {
            var cd = getFilePath(db_path, d);
            if (!new File(cd).mkdirs())
                throw new Exception(
                        String.format(
                                String.join(
                                        "\n",
                                        "Database '%s' not created",
                                        "Unsuccessful attempt to create a service folder '%s'"
                                ),
                                name,
                                cd
                        )
                );
        }
    }

    public void writeTableHeader(String dataBaseName, Table table) throws Exception {
        try (var out = new Output(new FileOutputStream(getFilePath(db_path(dataBaseName), tableHeaders, table.getTableName())))) {
            kryo.writeObject(out, table);
        }
    }

    public void initTableData(String dataBaseName, String tableName) throws Exception {
        try (var ignored = new FileOutputStream(getFilePath(db_path(dataBaseName), tableData, tableName))) {
        }
    }

    public Table readTable(File file) throws Exception {
        try (var in = new Input(new FileInputStream(file))) {
            return kryo.readObject(in, Table.class);
        }
    }

    public void appendRow(String dataBaseName, String tableName, ArrayList row) throws Exception {
        try (var out = new Output(new FileOutputStream(getFilePath(db_path(dataBaseName), tableData, tableName), true))) {
            kryo.writeObject(out, row);
        }
    }

    public RowIterator getRowIterator(String dataBaseName, String tableName) throws Exception {
        return new RowIterator(new FileInputStream(getFilePath(db_path(dataBaseName), tableData, tableName)));
    }

    public String createTempTableData(String dataBaseName, String tableName) throws Exception {
        var tempTableName = tableName + tmpFileExtension;
        initTableData(dataBaseName, tempTableName);
        return tempTableName;
    }

    public void replaceTableDataToTemp(String dataBaseName, String tableName) throws Exception {
        var p = Paths.get(getFilePath(db_path(dataBaseName), tableData, tableName + tmpFileExtension));
        Files.move(p, p.resolveSibling(tableName), StandardCopyOption.REPLACE_EXISTING);
    }
}