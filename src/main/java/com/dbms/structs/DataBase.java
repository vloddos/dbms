package com.dbms.structs;

import com.dbms.storage.StorageEngine;

import java.util.HashMap;
import java.util.Map;

public class DataBase {//immutable methods???

    private String name;
    private Map<String, Table> tables = new HashMap<>();//all meta_data must be load

    public DataBase(String name) {
        this.name = name;
    }

    public DataBase(String name, Map<String, Table> tables) {
        this.name = name;
        this.tables = tables;
    }

    public String getName() {
        return name;
    }

    public void createTable(String tableName, Map<String, TypeDescription> fields) throws Exception {
        if (tables.containsKey(tableName))
            throw new Exception(String.format("Table '%s' already exists", tableName));

        var table = new Table(name, tableName, fields);
        tables.put(tableName, table);
        StorageEngine.writeTableHeader(name, table);
        StorageEngine.initTableData(name, tableName);
    }

    public Table getTable(String name) throws Exception {
        if (!tables.containsKey(name))
            throw new Exception(String.format("Table '%s' does not exist", name));

        return tables.get(name);
    }
}