package com.dbms.storage.data;

import com.dbms.storage.Serialization;
import com.dbms.storage.file_structs.BlockExtendedFileStruct;
import com.dbms.storage.file_structs.FileStruct;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// FIXME: 18.12.2018 проверить возможность обновить крио(многопоточность)
// FIXME: 25.12.2018 rwlock+threadlocal???
public class MetaDataManager {

    private static MetaDataManager instance;

    public ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(Serialization.getInstance()::getKryo);

    // FIXME: 03.12.2018 как то поменять isFile/isDirectory???
    private MetaDataManager() {
    }

    public static MetaDataManager getInstance() {
        if (instance == null)
            instance = new MetaDataManager();

        return instance;
    }

    /*public void updateKryo() {
        kryoLock.lock();
        try {
            kryo = Serialization.getInstance().getKryo();
        } finally {
            kryoLock.unlock();
        }
    }*/

    public boolean databaseExists(String databaseName) {
        return new File(FileStruct.getDatabasePath(databaseName)).exists();
    }

    public <D> D readDatabase(String databaseName, Class<D> databaseClass) throws Exception {
        try (
                var in = new Input(
                        new FileInputStream(
                                FileStruct.getDatabasePath(databaseName)
                        )
                )
        ) {
            return kryoThreadLocal.get().readObject(in, databaseClass);
        }
    }

    public <D> Map<String, D> readAllDatabases(Class<D> databaseClass) throws Exception {
        var databases = new HashMap<String, D>();

        for (var f : new File(FileStruct.getDatabaseRootDirectoryPath()).listFiles(File::isDirectory)) {//must not throw exception
            var databaseName = f.getName();//f is directory
            databases.put(
                    databaseName,
                    readDatabase(
                            databaseName,
                            databaseClass
                    )
            );
        }

        return databases;
    }

    public <D> void writeDatabase(String databaseName, D database) throws Exception {
        try (
                var out = new Output(
                        new FileOutputStream(
                                FileStruct.getDatabasePath(databaseName)
                        )
                )
        ) {
            kryoThreadLocal.get().writeObject(out, database);
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

    public <T> T readTable(String databaseName, String tableName, Class<T> tableClass) throws Exception {
        try (
                var in = new Input(
                        new FileInputStream(
                                FileStruct.getTableFullPath(databaseName, tableName)
                        )
                )
        ) {
            var tmp = kryoThreadLocal.get().readObject(in, tableClass);
            System.out.println(in.position());
            return tmp;
        }
    }

    public <T> Map<String, T> readAllTables(String databaseName, Class<T> tableClass) throws Exception {
        var tables = new HashMap<String, T>();

        for (var f : new File(FileStruct.getTablesFullPath(databaseName)).listFiles(File::isFile)) {
            var tableName = FilenameUtils.getBaseName(f.getName());
            tables.put(
                    tableName,
                    readTable(
                            databaseName,
                            tableName,
                            tableClass
                    )
            );
        }

        return tables;
    }

    public <T> void writeTable(String databaseName, String tableName, T table) throws Exception {
        try (
                var out = new Output(
                        new FileOutputStream(
                                FileStruct.getTableFullPath(databaseName, tableName)
                        )
                )
        ) {
            kryoThreadLocal.get().writeObject(out, table);
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
        for (var d : BlockExtendedFileStruct.getDatabaseServiceDirectoryFullPaths(name)) {
            if (!new File(d).mkdirs()) {//???
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
            }
        }

        writeDatabase(name, database);
    }
}