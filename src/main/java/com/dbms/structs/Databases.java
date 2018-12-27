package com.dbms.structs;

import com.dbms.storage.data.MetaDataManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Only this class should be used to access databases.
 * So if you make a database object using a constructor, it will be wrong to work with such an object.
 * Before you start working with DBMS, you need to write the following:
 * <pre>{@code
 * BlockExtendedFileStruct.init();
 * Databases.getInstance().fullLoad();
 * }</pre>
 */
public class Databases {

    private Databases() {
    }

    private static Databases instance;

    public static Databases getInstance() {
        if (instance == null)
            instance = new Databases();

        return instance;
    }

    private Database currentDatabase;
    private Map<String, Database> databases = new HashMap<>();

    /**
     * Downloads all available databases.
     */
    /*public void fullLoad() throws Exception {
        databases = MetaDataManager.getInstance().readAllDatabases(Table.class);

        var o = databases.keySet().stream().findFirst();
        if (o.isPresent())
            useDatabase(o.get());
        //start checker...
    }*/

    /**
     * Creates a database with the specified name.
     */
    public void createDatabase(String name) throws Exception {
        MetaDataManager.getInstance().initDatabase(name);
        databases.put(name, new Database(name));
        useDatabase(name);
    }

    /**
     * Makes the database with the specified name current.
     */
    public Database useDatabase(String name) throws Exception {
        return currentDatabase = getDatabase(name);
    }

    /**
     * @return the database with the specified name.
     * @throws Exception
     */
    public Database getDatabase(String name) throws Exception {
        /*if (!databases.containsKey(name))
            databases.put(name, MetaDataManager.getInstance().readDatabase(name));*/

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

    /*
    на вход нужно подать предобработанный лимит,офсет,where
    то есть, если не было указано лимита, то он должен быть равен Integer.MAX_VALUE,
    если не было указано офсета, то он должен быть равен 0,
    если не было указано where, то он должен быть равен null
    */

    /*public TextTable select(Vector<String> fieldNames, int limit, int offset, String tableName, String where) throws Exception {//move to table???
        var table = currentDatabase.getTable(tableName);
        var fieldIndexes = fieldNames.stream().map(table::getFieldIndex).collect(Collectors.toList());
        var whereExpression = table.where(where, "r");

        var js = ScriptManager.scriptEngineManager.getEngineByName("js");

        var rowIterator = MetaDataManager.getInstance().getRowIterator(currentDatabase.getName(), tableName);
        IntStream.range(0, offset).forEach(i -> rowIterator.next());
        var rows = new Vector<Vector<?>>();
        for (int i = 0; i < limit && rowIterator.hasNext(); ++i) {
            var r = rowIterator.next();
            js.put("r", r);
            if (where == null || ((boolean) js.eval(whereExpression.getValueForEval()))) {
                var row = new Vector<>();
                fieldIndexes.forEach(j -> row.add(r.get(j)));
                rows.add(row);
            }
        }
        return new TextTable(new DefaultTableModel(rows, fieldNames));
    }*/

    //show databases???
    //alter new thread???
}