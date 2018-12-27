package com.dbms.structs;

import com.dbms.storage.data.DataManager;
import com.dbms.storage.data.MetaDataManager;

import java.util.HashMap;
import java.util.Map;

public class Database {//immutable methods???

    private String name;
    private Map<String, Table> tables = new HashMap<>();//all meta_data must be load

    public Database(String name) {
        this.name = name;
    }

    public Database(String name, Map<String, Table> tables) {
        this.name = name;
        this.tables = tables;
    }

    public String getName() {
        return name;
    }

    /**
     * Creates a table with the specified name and fields.
     */
    public void createTable(String tableName, Map<String, TypeDescription> fields) throws Exception {
        if (tables.containsKey(tableName))
            throw new Exception(String.format("Table '%s' already exists", tableName));

        var table = new Table(name, tableName, fields);
        tables.put(tableName, table);
        MetaDataManager.getInstance().writeTable(name, tableName, table);
        DataManager.getInstance().initTableData(name, tableName);
    }

    /**
     * @return the table with the specified name.
     * @throws Exception if the table with the specified name does not exist
     */
    public Table getTable(String name) throws Exception {
        if (!tables.containsKey(name))
            throw new Exception(String.format("Table '%s' does not exist", name));

        return tables.get(name);
    }
}