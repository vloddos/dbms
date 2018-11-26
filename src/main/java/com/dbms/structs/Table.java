package com.dbms.structs;

import com.dbms.scripting.Expression;
import com.dbms.scripting.ScriptManager;
import com.dbms.storage.StorageEngine;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Table {

    private String dataBaseName;
    private String tableName;

    public Map<String, TypeDescription> fieldDescriptions;//must be LinkedHashMap
    private Map<String, Integer> fieldIndexes = new HashMap<>();

    //for kryo
    private Table() {
    }

    public Table(String dataBaseName, String tableName, Map<String, TypeDescription> fieldDescriptions) throws RuntimeException {
        fieldDescriptions.forEach(
                (k, v) -> {
                    var typeName = v.getName();
                    if (!Types.haveType(typeName))
                        throw new RuntimeException(
                                String.format(
                                        "Attempting to create a table '%s' was specified the wrong data type '%s' of the field '%s'",
                                        tableName,
                                        typeName,
                                        k
                                )
                        );
                }
        );

        this.dataBaseName = dataBaseName;
        this.tableName = tableName;
        this.fieldDescriptions = fieldDescriptions;

        int[] i = {0};
        this.fieldDescriptions.keySet().forEach(k -> fieldIndexes.put(k, i[0]++));//linkedkeyset in linkedhashmap??? post inc???
    }

    public String getTableName() {
        return tableName;
    }

    public void throwIfNoField(String fieldName) throws RuntimeException {
        if (!fieldDescriptions.containsKey(fieldName))
            throw new RuntimeException(String.format("No such field '%s' in table '%s'", fieldName, tableName));
    }

    public int getFieldIndex(String fieldName) {
        throwIfNoField(fieldName);
        return fieldIndexes.get(fieldName);
    }

    public void insert(Map<String, String> fields) throws Exception {
        fields.keySet().forEach(this::throwIfNoField);

        var row = new ArrayList();
        IntStream.range(0, fieldDescriptions.size()).forEach(i -> row.add(null));

        this.fieldDescriptions.keySet().forEach(
                k -> row.set(
                        fieldIndexes.get(k),
                        Types.cast(
                                fields.get(k),
                                this.fieldDescriptions.get(k)
                        )
                )
        );
        StorageEngine.appendRow(dataBaseName, tableName, row);
    }

    public Expression where(String where, String rowName) {
        var fields = new HashMap<String, Pair<String, Integer>>();
        fieldIndexes.forEach((fn, i) -> fields.put(fn, new Pair<>(rowName, i)));
        return new Expression(where, fields);
    }

    public void delete(String where) throws Exception {
        if (where == null) {
            StorageEngine.initTableData(dataBaseName, tableName);
            return;
        }

        var tmp = StorageEngine.createTempTableData(dataBaseName, tableName);

        var whereExpression = where(where, "row");

        var js = ScriptManager.scriptEngineManager.getEngineByName("js");

        var rowIterator = StorageEngine.getRowIterator(dataBaseName, tableName);
        while (rowIterator.hasNext()) {
            var row = rowIterator.next();
            js.put("row", row);
            if (!((boolean) js.eval(whereExpression.getValueForEval())))
                StorageEngine.appendRow(dataBaseName, tmp, row);
        }

        StorageEngine.replaceTableDataToTemp(dataBaseName, tableName);
    }

    public void update(Map<String, String> set, String where) throws Exception {
        set.keySet().forEach(this::throwIfNoField);

        var tmp = StorageEngine.createTempTableData(dataBaseName, tableName);

        var whereExpression = where(where, "row");
        set.replaceAll((fn, e) -> new Expression(e, whereExpression.getFields()).getValueForEval());

        var js = ScriptManager.scriptEngineManager.getEngineByName("js");

        var rowIterator = StorageEngine.getRowIterator(dataBaseName, tableName);
        while (rowIterator.hasNext()) {
            var row = rowIterator.next();
            var tmpRow = ((ArrayList) row.clone());
            js.put("row", row);
            for (var entry : set.entrySet())
                if (where == null || ((boolean) js.eval(whereExpression.getValueForEval())))
                    tmpRow.set(
                            fieldIndexes.get(entry.getKey()),
                            js.eval(entry.getValue())
                    );

            StorageEngine.appendRow(dataBaseName, tmp, tmpRow);
        }

        StorageEngine.replaceTableDataToTemp(dataBaseName, tableName);
    }

    @Override
    public String toString() {
        return
                fieldDescriptions.entrySet().stream()
                        .map(e -> e.getKey() + " " + e.getValue())
                        .collect(Collectors.joining(",\n\t", "create table " + tableName + "(\n\t", "\n)"));
    }
}