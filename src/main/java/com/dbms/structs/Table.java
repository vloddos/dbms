package com.dbms.structs;

import com.dbms.scripting.Expression;
import com.dbms.scripting.ScriptManager;
import com.dbms.storage.data.MetaDataManager;
import com.dbms.storage.blocks.BlockManager;
import com.dbms.storage.blocks.BlockPointers;
import dnl.utils.text.table.TextTable;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Table {

    private String databaseName;
    private String tableName;

    private Map<String, TypeDescription> fieldDescriptions;//must be LinkedHashMap
    private Map<String, Integer> fieldIndexes = new HashMap<>();

    private BlockPointers blockPointers = new BlockPointers();

    //for kryo
    private Table() {
    }

    public Table(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @param databaseName the name of the database in which the table is located
     * @param tableName the name of the table
     * @param fieldDescriptions the field descriptions
     * @throws RuntimeException if the wrong data type was specified for any field
     */
    public Table(String databaseName, String tableName, Map<String, TypeDescription> fieldDescriptions) throws RuntimeException {
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

        this.databaseName = databaseName;
        this.tableName = tableName;
        this.fieldDescriptions = fieldDescriptions;

        int[] i = {0};
        this.fieldDescriptions.keySet().forEach(k -> fieldIndexes.put(k, i[0]++));//linkedkeyset in linkedhashmap??? post inc???
    }

    public String getTableName() {
        return tableName;
    }

    /**
     * @return the name of the database in which the table is located
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * @param fieldName the name of the field whose presence is to be checked
     * @throws RuntimeException if there is no such field in the table
     */
    public void throwIfNoField(String fieldName) throws RuntimeException {
        if (!fieldDescriptions.containsKey(fieldName))
            throw new RuntimeException(String.format("No such field '%s' in table '%s'", fieldName, tableName));
    }

    /**
     * @param fieldName the name of the field whose index is to be got
     * @throws RuntimeException if there is no such field in the table
     */
    public int getFieldIndex(String fieldName) {
        throwIfNoField(fieldName);
        return fieldIndexes.get(fieldName);
    }

    /**
     * Inserts 1 row to the table.
     *
     * @param fields key-value map: key - field name, value - string value for inserting, casts to the corresponding type
     * @throws RuntimeException if there is no such field in the table
     */
    public void insert(Map<String, String> fields) throws Exception {
        fields.keySet().forEach(this::throwIfNoField);

        var row = new ArrayList<>();
        IntStream.range(0, fieldDescriptions.size()).forEach(i -> row.add(null));

        fieldDescriptions.keySet().forEach(
                k -> row.set(
                        fieldIndexes.get(k),
                        Types.cast(
                                fields.get(k),
                                fieldDescriptions.get(k)
                        )
                )
        );

        BlockManager.getInstance().appendElement(databaseName, tableName, blockPointers, row);
        MetaDataManager.getInstance().writeTable(databaseName, tableName, this);// FIXME: 02.12.2018 write only if bp was changed???
    }

    /**
     * @param where   string with where clause
     * @param rowName
     * @return
     */
    public Expression where(String where, String rowName) {
        var fields = new HashMap<String, Pair<String, Integer>>();
        fieldIndexes.forEach((fn, i) -> fields.put(fn, new Pair<>(rowName, i)));
        return new Expression(where, fields);
    }

    public TextTable select(Vector<String> fieldNames, int limit, int offset, String where) throws Exception {
        var fieldIndexes = fieldNames.stream().map(this::getFieldIndex).collect(Collectors.toList());
        var whereExpression = where(where, "r");

        var js = ScriptManager.scriptEngineManager.getEngineByName("js");

        return null;
        /*var rowIterator = MetaDataManager.getInstance().getRowIterator(databaseName, tableName);
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
        return new TextTable(new DefaultTableModel(rows, fieldNames));*/
    }
    /*public void delete(String where) throws Exception {//rewrite byte arrays of not deleted data???
        if (where == null) {
            MetaDataManager.getInstance().initTableData(databaseName, tableName);
            return;
        }

        var tmp = MetaDataManager.getInstance().createTempTableData(databaseName, tableName);

        var whereExpression = where(where, "row");

        var js = ScriptManager.scriptEngineManager.getEngineByName("js");

        var rowIterator = MetaDataManager.getInstance().getRowIterator(databaseName, tableName);
        while (rowIterator.hasNext()) {
            var row = rowIterator.next();
            js.put("row", row);
            if (!((boolean) js.eval(whereExpression.getValueForEval())))
                MetaDataManager.getInstance().appendData(databaseName, tmp, row);
        }

        MetaDataManager.getInstance().replaceTableDataToTemp(databaseName, tableName);
    }

    public void update(Map<String, String> set, String where) throws Exception {//rewrite byte arrays of not changed data???
        set.keySet().forEach(this::throwIfNoField);

        var tmp = MetaDataManager.getInstance().createTempTableData(databaseName, tableName);

        var whereExpression = where(where, "row");
        set.replaceAll((fn, e) -> new Expression(e, whereExpression.getFields()).getValueForEval());

        var js = ScriptManager.scriptEngineManager.getEngineByName("js");

        var rowIterator = MetaDataManager.getInstance().getRowIterator(databaseName, tableName);
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

            MetaDataManager.getInstance().appendData(databaseName, tmp, tmpRow);
        }

        MetaDataManager.getInstance().replaceTableDataToTemp(databaseName, tableName);
    }*/

    @Override
    public String toString() {
        return
                fieldDescriptions.entrySet().stream()
                        .map(e -> e.getKey() + " " + e.getValue())
                        .collect(Collectors.joining(",\n\t", "create table " + tableName + "(\n\t", "\n)"));
    }
}