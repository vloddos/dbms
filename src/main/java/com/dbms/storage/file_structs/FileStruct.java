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
    protected static String databaseRootDirectory = "db";

    //database service directory names
    protected static String metaData = "meta data";
    protected static String tables = "tables";

    protected static String data = "data";
    protected static String tableData = "table data";

    //file extensions
    protected static String tmpFileExtension = ".tmp";
    protected static String databaseExtension = ".db";
    protected static String tableExtension = ".t";
    protected static String tableDataExtension = ".d";

    public static void init() throws Exception {
        var dbrd = new File(getDatabaseRootDirectoryPath());
        if (!dbrd.isDirectory())
            if (!dbrd.mkdir())
                throw new Exception("Cannot create database root directory");
    }

    //dbms service directories getters
    public static String getDatabaseRootDirectoryPath() {
        return databaseRootDirectory;
    }

    //database service directories getters
    public static String getMetaDataRelativePath() {
        return metaData;
    }

    public static String getTablesRelativePath() {
        return getFilePath(getMetaDataRelativePath(), tables);
    }

    public static String getDataRelativePath() {
        return data;
    }

    public static String getTableDataRelativePath() {
        return getFilePath(getDataRelativePath(), tableData);
    }

    public static ArrayList<String> getDatabaseServiceDirectoryRelativePaths() {
        var al = new ArrayList<String>();
        al.add(getTablesRelativePath());
        al.add(getTableDataRelativePath());
        return al;
    }

    //file extensions getters
    public static String getTmpFileExtension() {
        return tmpFileExtension;
    }

    public static String getDatabaseExtension() {
        return databaseExtension;
    }

    public static String getTableExtension() {
        return tableExtension;
    }

    public static String getTableDataExtension() {
        return tableDataExtension;
    }

    //struct paths getters
    public static String getDatabaseDirectoryPath(String name) {
        return getFilePath(getDatabaseRootDirectoryPath(), name);
    }

    public static String getDatabasePath(String name) {
        return getFilePath(getDatabaseDirectoryPath(name), name + databaseExtension);
    }

    public static String getMetaDataFullPath(String databaseName) {
        return getFilePath(getDatabaseDirectoryPath(databaseName), getMetaDataRelativePath());
    }

    public static String getDataFullPath(String databaseName) {
        return getFilePath(getDatabaseDirectoryPath(databaseName), getDataRelativePath());
    }

    public static String getTablesFullPath(String databaseName) {
        return getFilePath(getDatabaseDirectoryPath(databaseName), getTablesRelativePath());
    }

    public static String getTableFullPath(String databaseName, String tableName) {
        return getFilePath(getTablesFullPath(databaseName), tableName + tableExtension);
    }

    public static String getTableDatasFullPath(String databaseName) {
        return getFilePath(getDatabaseDirectoryPath(databaseName), getTableDataRelativePath());
    }

    public static String getTableDataFullPath(String databaseName, String tableName) {//1 data file per table
        return getFilePath(getTableDatasFullPath(databaseName), tableName + tableDataExtension);
    }
}