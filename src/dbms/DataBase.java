package dbms;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DataBase implements Serializable {//immutable methods???

    private static final long serialVersionUID = -3880267854538026269L;

    public String name;
    public Map<String, Table> tables = new LinkedHashMap<>();

    //hash database???
    //changed rows/cells???
    //views???
    //functions???

    public DataBase(String name) {
        this.name = name;
    }

    public synchronized Table createTable(Table table) throws Exception {
        if (tables.containsKey(table.header.name))
            throw new Exception(String.format("The '%s' table already exists", table.header.name));

        tables.put(table.header.name, table);
        return table;//???
    }

    public String showCreate(String name) throws Exception {
        if (!tables.containsKey(name))
            throw new Exception(String.format("The '%s' table does not exist", name));

        var f = tables.get(name).header.fields;
        return f.keySet().stream()
                .map(k -> k + " " + f.get(k))
                .collect(Collectors.joining(",\n\t", "create table " + name + "(\n\t", "\n)"));
    }
}