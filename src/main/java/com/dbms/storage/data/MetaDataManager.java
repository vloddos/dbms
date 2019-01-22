package com.dbms.storage.data;

import com.dbms.storage.file_structs.BlockExtendedFileStruct;
import com.dbms.storage.file_structs.FileStruct;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

// FIXME: 25.12.2018 rwlock+threadlocal???
public class MetaDataManager {

    private static MetaDataManager instance;

    // FIXME: 03.12.2018 как то поменять isFile/isDirectory???
    private MetaDataManager() {
    }

    public static MetaDataManager getInstance() {
        if (instance == null)
            instance = new MetaDataManager();

        return instance;
    }

    public boolean databaseExists(String databaseName) {
        return new File(FileStruct.getDatabasePath(databaseName)).exists();
    }

    public <D> D readDatabase(String databaseName) throws Exception {
        try (
                var in = new ObjectInputStream(
                        new FileInputStream(
                                FileStruct.getDatabasePath(databaseName)
                        )
                )
        ) {
            return (D) in.readObject();
        }
    }

    public <D> Map<String, D> readAllDatabases() throws Exception {
        var databases = new HashMap<String, D>();

        for (var f : new File(FileStruct.getDatabaseRootDirectoryPath()).listFiles(File::isDirectory)) {//must not throw exception
            var databaseName = f.getName();//f is directory
            databases.put(
                    databaseName,
                    readDatabase(databaseName)
            );
        }

        return databases;
    }

    public <D> void writeDatabase(String databaseName, D database) throws Exception {
        try (
                var out = new ObjectOutputStream(
                        new FileOutputStream(
                                FileStruct.getDatabasePath(databaseName)
                        )
                )
        ) {
            out.writeObject(database);
        }
    }

    public void deleteDatabase(String databaseName) throws Exception {//fixme move to DM???
        try {
            FileUtils.deleteDirectory(new File(FileStruct.getDatabaseDirectoryPath(databaseName)));
        } catch (IOException e) {
            throw new Exception(
                    String.format("Unsuccessful deletion of the database '%s'", databaseName),
                    e
            );
        }
    }

    public boolean tableExists(String databaseName, String tableName) {
        return new File(FileStruct.getTableFullPath(databaseName, tableName)).exists();
    }

    public <T> T readTable(String databaseName, String tableName) throws Exception {
        try (
                var in = new ObjectInputStream(
                        new FileInputStream(
                                FileStruct.getTableFullPath(databaseName, tableName)
                        )
                )
        ) {
            return (T) in.readObject();
        }
    }

    public <T> Map<String, T> readAllTables(String databaseName) throws Exception {
        var tables = new HashMap<String, T>();

        for (var f : new File(FileStruct.getTablesFullPath(databaseName)).listFiles(File::isFile)) {
            var tableName = FilenameUtils.getBaseName(f.getName());
            tables.put(
                    tableName,
                    readTable(
                            databaseName,
                            tableName
                    )
            );
        }

        return tables;
    }

    public <T> void writeTable(String databaseName, String tableName, T table) throws Exception {
        try (
                var out = new ObjectOutputStream(
                        new FileOutputStream(
                                FileStruct.getTableFullPath(databaseName, tableName)
                        )
                )
        ) {
            out.writeObject(table);
        }
    }

    public void deleteTable(String databaseName, String tableName) throws RuntimeException {
        if (!new File(FileStruct.getTableFullPath(databaseName, tableName)).delete())
            throw new RuntimeException(
                    String.format(
                            "Unsuccessful deletion of the table '%s' from the database '%s'",
                            tableName,
                            databaseName
                    )
            );
    }

    public <D> void initDatabase(String name, D database) throws Exception {
        for (var d : BlockExtendedFileStruct.getDatabaseServiceDirectoryFullPaths(name))
            if (!new File(d).mkdirs())//???
                try {
                    deleteDatabase(name);
                } finally {
                    throw new Exception(
                            String.format(
                                    String.join(
                                            "\n",
                                            "The database '%s' is not created",
                                            "Unsuccessful attempt to create a service folder '%s'"
                                    ),
                                    name,
                                    d
                            )
                    );
                }

        writeDatabase(name, database);
    }
}