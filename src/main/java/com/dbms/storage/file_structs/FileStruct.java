package com.dbms.storage.file_structs;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileStruct {// TODO: 03.12.2018 геттеры возвращающие/принимающие файлы???

    protected FileStruct() {
    }

    public static String getFilePath(String first, String... more) {
        return Paths.get(first, more).toString();
    }

    //dbms service directory names
    private static String databaseRootDirectory = "db";

    //database service directory names
    private static String metaData = "meta data";
    private static String tables = "tables";

    private static String data = "data";
    private static String tableData = "table data";

    //file extensions
    private static String tmpFileExtension = ".tmp";

    public static void init() throws Exception {
        var dbrd = new File(getDatabaseRootDirectory());
        if (!dbrd.isDirectory())
            if (!dbrd.mkdir())
                throw new Exception("Cannot create database root directory");
    }

    //dbms service directories getters
    public static String getDatabaseRootDirectory() {
        return databaseRootDirectory;
    }

    //database service directories getters
    public static String getMetaData() {
        return metaData;
    }

    public static String getTables() {
        return getFilePath(getMetaData(), tables);
    }

    public static String getData() {
        return data;
    }

    public static String getTableData() {
        return getFilePath(getData(), tableData);
    }

    public static ArrayList<String> getDbDirectories() {
        var al = new ArrayList<String>();
        al.add(getTables());
        al.add(getTableData());
        return al;
    }

    //file extensions getters
    public static String getTmpFileExtension() {
        return tmpFileExtension;
    }

    //struct paths getters
    public static String getDatabasePath(String name) {
        return getFilePath(getDatabaseRootDirectory(), name);
    }

    public static String getTablesPath(String databaseName) {
        return getFilePath(getDatabasePath(databaseName), getTables());
    }

    public static String getTablePath(String databaseName, String tableName) {
        return getFilePath(getTablesPath(databaseName), tableName);
    }

    public static String getTableDataPath(String databaseName) {
        return getFilePath(getDatabasePath(databaseName), getData());
    }

    // FIXME: 03.12.2018 rename???
    public static String getTableDataPath(String databaseName, String tableName) {//1 data file per table(data without blocks)
        return getFilePath(getTableDataPath(databaseName), tableName);
    }
}