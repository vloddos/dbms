package com.dbms.structs;

import com.dbms.storage.data.MetaDataManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Only this class should be used to access databases.
 * So if you make a database object using a constructor, it will be wrong to work with such an object.
 * Before you start working with DBMS, you need to write the following:
 * <pre>{@code
 * //register all classes that need to be serialized using {@link com.dbms.storage.Serialization}...
 * BlockManager.init();
 * BlockExtendedFileStruct.init();
 * Databases.getInstance().fullDatabasesFullTablesLoad();
 * }</pre>
 */
public class Databases {

    public enum Load {
        LAZY,
        FULL
    }

    private static Databases instance;

    private Database currentDatabase;
    private Map<String, Database> databases = new HashMap<>();

    private Load tablesLoad = Load.LAZY;

    public static Databases getInstance() {
        if (instance == null)
            instance = new Databases();

        return instance;
    }

    private Databases() {
    }

    /**
     * Downloads all available databases.
     */
    public void fullDatabasesFullTablesLoad() throws Exception {
        databases = MetaDataManager.getInstance().readAllDatabases(Database.class);
        for (var d : databases.values())
            d.fullTablesLoad();

        var o = databases.keySet().stream().findFirst();
        if (o.isPresent())
            useDatabase(o.get());
        //start checker...???
        tablesLoad = Load.FULL;
    }

    /**
     * Creates a database with the specified name.
     */
    public void createDatabase(String name) throws Exception {
        if (MetaDataManager.getInstance().databaseExists(name))
            throw new Exception(String.format("The database '%s' already exists", name));

        var database = new Database(name);
        MetaDataManager.getInstance().initDatabase(name, database);
        databases.put(name, database);
        currentDatabase = database;//useDatabase(name);???
    }

    public void dropDatabase(String name) throws Exception {// TODO: 16.12.2018 check
        if (currentDatabase == getDatabase(name))//if database does not exist then will be thrown an exception
            currentDatabase = null;
        databases.remove(name);

        MetaDataManager.getInstance().deleteDatabase(name);
    }

    /**
     * Makes the database with the specified name current.
     */
    public Database useDatabase(String name) throws Exception {
        return currentDatabase = getDatabase(name);
    }

    /**
     * @return the database with the specified name
     * @throws Exception if the database with the specified name does not exist
     */
    public Database getDatabase(String name) throws Exception {
        if (!MetaDataManager.getInstance().databaseExists(name))
            throw new Exception(String.format("The database '%s' does not exist", name));

        if (!databases.containsKey(name)) {
            databases.put(name, MetaDataManager.getInstance().readDatabase(name, Database.class));
            if (tablesLoad == Load.FULL)
                databases.get(name).fullTablesLoad();
        }

        return databases.get(name);
    }

    /**
     * @return current database(which was specified by useDatabase).
     * @throws Exception if currentDatabase==null
     */
    public Database getCurrentDatabase() throws Exception {
        if (currentDatabase == null)
            throw new Exception("No database selected");

        return currentDatabase;
    }

    /**
     * Exits the DBMS.
     * It must be called before you exit from the DBMS.
     */
    public void exit() {
        System.exit(0);
        //shutdown executors...
        //не перезаписывать часто на диск
        /*databases.forEach(//если не удалось записать 1 бд то должна быть попытка записать остальные
                (k, v) -> {
                    try {
                        writeDataBase(v);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );*/
    }
    //show databases???
    //alter new thread???
}