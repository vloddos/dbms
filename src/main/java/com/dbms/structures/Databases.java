package com.dbms.structures;

import com.dbms.storage.data.MetaDataManager;
import com.dbms.storage.serialization.Serialization;

import java.util.HashMap;
import java.util.Map;

/**
 * Only this class should be used to access databases.
 * So if you make a database object using a constructor, it will be wrong to work with such an object.
 * Before you start working with DBMS, you need to write the following:
 * <pre>{@code
 * //register all classes that need to be serialized using {@link Serialization }...
 * BlockManager.init();
 * BlockExtendedFileStruct.init();
 * Databases.getInstance().fullDatabasesFullTablesLoad();//or another load
 * }</pre>
 */
// TODO: 25.01.2019 доработать lazy/full loads для многопоточности
public class Databases {

    public enum Load {
        LAZY,
        FULL
    }

    private static Databases instance;

    private ThreadLocal<Database> currentDatabase = new ThreadLocal<>();
    private Map<String, Database> databases = new HashMap<>();

    private ThreadLocal<Load> databasesLoad = ThreadLocal.withInitial(() -> Load.LAZY);
    private ThreadLocal<Load> tablesLoad = ThreadLocal.withInitial(() -> Load.LAZY);

    public static Databases getInstance() {
        if (instance == null)
            instance = new Databases();

        return instance;
    }

    private Databases() {
    }

    //load type setters если нужно переключить режим и не хочется повторно перезагружать бд и таблицы
    public void setLazyDatabasesLazyTablesLoad() throws Exception {
        databasesLoad.set(Load.LAZY);
        tablesLoad.set(Load.LAZY);
    }

    public void setLazyDatabasesFullTablesLoad() throws Exception {
        databasesLoad.set(Load.LAZY);
        tablesLoad.set(Load.FULL);
    }

    public void setFullDatabasesLazyTablesLoad() throws Exception {
        databasesLoad.set(Load.FULL);
        tablesLoad.set(Load.LAZY);
    }

    public void setFullDatabasesFullTablesLoad() throws Exception {
        databasesLoad.set(Load.FULL);
        tablesLoad.set(Load.FULL);
    }

    //loads
    public void lazyDatabasesLazyTablesLoad() throws Exception {
        setLazyDatabasesLazyTablesLoad();
    }

    public void lazyDatabasesFullTablesLoad() throws Exception {
        setLazyDatabasesFullTablesLoad();
    }

    public void fullDatabasesLazyTablesLoad() throws Exception {
        databases = MetaDataManager.getInstance().readAllDatabases();

        databases.values().stream().findFirst().ifPresent(currentDatabase::set);

        setFullDatabasesLazyTablesLoad();
    }

    public void fullDatabasesFullTablesLoad() throws Exception {
        fullDatabasesLazyTablesLoad();

        for (var d : databases.values())
            d.fullTablesLoad();

        setFullDatabasesFullTablesLoad();
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
        currentDatabase.set(database);
    }

    public void dropDatabase(String name) throws Exception {// TODO: 16.12.2018 check
        if (!MetaDataManager.getInstance().databaseExists(name))
            throw new Exception(String.format("The database '%s' does not exist", name));

        if (currentDatabase.get() == databases.get(name))
            currentDatabase.set(null);
        databases.remove(name);

        MetaDataManager.getInstance().deleteDatabase(name);
    }

    /**
     * Makes the database with the specified name current.
     */
    public Database useDatabase(String name) throws Exception {
        currentDatabase.set(getDatabase(name));
        return currentDatabase.get();
    }

    /**
     * @return the database with the specified name
     * @throws Exception if the database with the specified name does not exist
     */
    public Database getDatabase(String name) throws Exception {
        if (!MetaDataManager.getInstance().databaseExists(name))
            throw new Exception(String.format("The database '%s' does not exist", name));

        if (!databases.containsKey(name))
            databases.put(name, MetaDataManager.getInstance().readDatabase(name));

        var db = databases.get(name);
        if (tablesLoad.get() == Load.FULL && !db.isAllTablesLoaded())
            db.fullTablesLoad();

        return db;
    }

    /**
     * @return current database(which was specified by useDatabase).
     * @throws Exception if currentDatabase==null
     */
    public Database getCurrentDatabase() throws Exception {
        var cdb = currentDatabase.get();

        if (cdb == null)
            throw new Exception("No database selected");

        return cdb;
    }

    /**
     * Exits the DBMS.
     * It must be called before you exit from the DBMS.
     */
    public void exit() {
        //System.exit(0);
    }
}