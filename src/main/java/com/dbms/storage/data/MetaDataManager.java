package com.dbms.storage.data;

import com.dbms.global.Global;
import com.dbms.storage.file_structs.BlockExtendedFileStruct;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class MetaDataManager {// TODO: 03.12.2018 TableMetaData???

    private HashMap<Class<?>, Constructor<?>> databaseConstructors = new HashMap<>();

    // FIXME: 03.12.2018 как то поменять isFile/isDirectory???
    private MetaDataManager() {
    }

    private static MetaDataManager instance;

    public static MetaDataManager getInstance() {
        if (instance == null)
            instance = new MetaDataManager();

        return instance;
    }

    private Kryo kryo = Global.getKryo();

    public <D, T> D readDatabase(File databaseDirectory, Class<D> databaseClass, Class<T> tableClass) throws Exception {
        var databaseName = databaseDirectory.getName();
        if (!databaseDirectory.isDirectory())
            throw new Exception(String.format("The '%s' database does not exist", databaseName));

        //read tables
        var tables = new HashMap<String, T>();
        for (var f : new File(BlockExtendedFileStruct.getTablesPath(databaseDirectory.toString())).listFiles(File::isFile))
            tables.put(f.getName(), readTable(f, tableClass));

        databaseClass.getConstructor(String.class, HashMap.class).newInstance(databaseName, tables);
        //return
    }

    public <T> DatabaseMetaData<T> readDatabase(String name, Class<T> tableClass) throws Exception {
        return readDatabase(new File(BlockExtendedFileStruct.getDatabasePath(name)), tableClass);
    }

    public <T> Map<String, DatabaseMetaData<T>> readAllDatabases(Class<T> tableClass) throws Exception {
        var databases = new HashMap<String, DatabaseMetaData<T>>();

        for (var f : new File(BlockExtendedFileStruct.getDatabaseRootDirectory()).listFiles(File::isDirectory))
            databases.put(f.getName(), readDatabase(f, tableClass));//must not throw exception

        return databases;
    }

    public void initDatabase(String name) throws Exception {
        var databasePath = BlockExtendedFileStruct.getDatabasePath(name);

        if (new File(databasePath).exists())
            throw new Exception(String.format("The database '%s' already exists", name));

        for (var d : BlockExtendedFileStruct.getDbDirectories()) {
            var cd = BlockExtendedFileStruct.getFilePath(databasePath, d);
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

    public <T> T readTable(File file, Class<T> tableClass) throws Exception {
        try (var in = new Input(new FileInputStream(file))) {
            return kryo.readObject(in, tableClass);
        }
    }

    public <T> void writeTable(String databaseName, String tableName, T table) throws Exception {
        try (var out = new Output(new FileOutputStream(BlockExtendedFileStruct.getTablePath(databaseName, tableName)))) {
            kryo.writeObject(out, table);
        }
    }
}