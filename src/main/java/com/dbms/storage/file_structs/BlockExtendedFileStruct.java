package com.dbms.storage.file_structs;

import java.util.ArrayList;

public class BlockExtendedFileStruct extends FileStruct {// TODO: 03.12.2018 геттеры возвращающие/принимающие файлы???

    protected BlockExtendedFileStruct() {
    }

    //database service directory names
    private static String blocks = "blocks";//лежит в данных потому что удобнее и логичнее хранить блоки и хэдеры близко???
    private static String blockData = "block data";

    //file extensions
    private static String blockExtension = ".h";
    private static String blockDataExtension = ".b";

    //database service directories getters
    public static String getBlocks() {
        return getFilePath(getTableData(), blocks);
    }

    public static String getBlockData() {
        return getFilePath(getTableData(), blockData);
    }

    public static ArrayList<String> getDbDirectories() {
        var al = FileStruct.getDbDirectories();
        al.add(getBlocks());
        al.add(getBlockData());
        return al;
    }

    //file extensions getters
    public static String getBlockExtension() {
        return blockExtension;
    }

    public static String getBlockDataExtension() {
        return blockDataExtension;
    }

    //struct paths getters
    public static String getBlockPath(String databaseName, String tableName, String blockName) {
        return getFilePath(getDatabasePath(databaseName), getBlocks(), tableName, blockName);
    }

    public static String getBlockDataPath(String databaseName, String tableName, String blockDataName) {
        return getFilePath(getDatabasePath(databaseName), getBlockData(), tableName, blockDataName);
    }
}