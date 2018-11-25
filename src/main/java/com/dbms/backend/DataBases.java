package com.dbms.backend;

import dnl.utils.text.table.TextTable;

import javax.swing.table.DefaultTableModel;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataBases {

    private static DataBase currentDataBase;
    private static Map<String, DataBase> dataBases = new HashMap<>();

    private DataBases() {
    }

    public static void fullLoad() throws Exception {
        dataBases = StorageEngine.readAllDataBases();

        var o = dataBases.keySet().stream().findFirst();
        if (o.isPresent())
            useDataBase(o.get());
        //start checker...
    }

    public static void createDataBase(String name) throws Exception {
        StorageEngine.initDataBase(name);
        dataBases.put(name, new DataBase(name));
        useDataBase(name);
    }

    public static DataBase useDataBase(String name) throws Exception {
        return currentDataBase = getDataBase(name);
    }

    public static DataBase getDataBase(String name) throws Exception {
        if (!dataBases.containsKey(name))
            dataBases.put(name, StorageEngine.readDataBase(name));

        return dataBases.get(name);
    }

    public static DataBase getCurrentDataBase() throws Exception {
        if (currentDataBase == null)
            throw new Exception("No database selected");

        return currentDataBase;
    }

    public static void exit() {
        System.exit(0);
        //shutdown executors...
        //не перезаписывать часто на диск
        /*dataBases.forEach(//если не удалось записать 1 бд то должна быть попытка записать остальные
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
    public static TextTable select(Vector<String> fieldNames, int limit, int offset, String tableName, String where) throws Exception {
        var table = currentDataBase.getTable(tableName);
        var fieldIndexes = fieldNames.stream().map(table::getFieldIndex).collect(Collectors.toList());
        var whereExpression = table.where(where, "r");

        var js = ScriptManager.scriptEngineManager.getEngineByName("js");

        var rowIterator = StorageEngine.getRowIterator(currentDataBase.getName(), tableName);
        IntStream.range(0, offset).forEach(i -> rowIterator.next());
        var rows = new Vector<Vector>();
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
    }

    //show databases???
    //alter new thread???
}