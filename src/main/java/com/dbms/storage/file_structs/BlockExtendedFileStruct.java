package com.dbms.storage.file_structs;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BlockExtendedFileStruct extends FileStruct {// TODO: 03.12.2018 геттеры возвращающие/принимающие файлы???

    protected BlockExtendedFileStruct() {
    }

    //database service directory names
    private static String blocksPointers = "blocks pointers";

    //file extensions
    private static String blocksPointerExtension = ".bp";

    //database service directories getters
    public static String getBlocksPointersRelativePath() {
        return getFilePath(getMetaDataRelativePath(), blocksPointers);
    }

    public static ArrayList<String> getDatabaseServiceDirectoryRelativePaths() {
        var al = FileStruct.getDatabaseServiceDirectoryRelativePaths();
        al.add(getBlocksPointersRelativePath());
        return al;
    }

    public static ArrayList<String> getDatabaseServiceDirectoryFullPaths(String databaseName) {
        var al = getDatabaseServiceDirectoryRelativePaths();
        var dbp = getDatabaseDirectoryPath(databaseName);
        return al.stream().map(p -> getFilePath(dbp, p)).collect(Collectors.toCollection(ArrayList::new));
    }

    //file extensions getters
    public static String getBlocksPointerExtension() {
        return blocksPointerExtension;
    }

    //struct paths getters
    public static String getBlocksPointersFullPath(String databaseName) {
        return getFilePath(getDatabaseDirectoryPath(databaseName), getBlocksPointersRelativePath());
    }

    public static String getBlocksPointerFullPath(String databaseName, String tableName) {
        return getFilePath(getBlocksPointersFullPath(databaseName), tableName + blocksPointerExtension);
    }
}