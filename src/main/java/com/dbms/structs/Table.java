package com.dbms.structs;

import com.dbms.scripting.Expression;
import com.dbms.scripting.ScriptManager;
import com.dbms.storage.blocks.BlockManager;
import com.dbms.storage.blocks.BlocksPointer;
import com.dbms.storage.data.Row;
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

    private transient BlocksPointer blocksPointer;

    private int rowLength;

    //for kryo
    private Table() {
    }

    public Table(String tableName) {
        this.tableName = tableName;
    }

    public BlocksPointer getBlocksPointer() throws Exception {
        if (blocksPointer == null)
            blocksPointer = Databases.getInstance().getDatabase(databaseName).getBlocksPointer(tableName);

        return blocksPointer;
    }

    /**
     * @param databaseName      the name of the database in which the table is located
     * @param tableName         the name of the table
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

        rowLength = Row.SIZE_OF_SERVICE_DATA;
        fieldDescriptions.values().forEach(
                td -> {
                    var l = td.getLength();
                    rowLength += ((l == -1) ? Types.getSize(td.getName()) : Types.getSize(td.getName()) * l);
                }
        );
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
    public void insert(Map<String, String> fields) throws Exception {// FIXME: 16.12.2018 обрезать внешние кавычки строк???
        fields.keySet().forEach(this::throwIfNoField);

        var al = new ArrayList<>();
        IntStream.range(0, fieldDescriptions.size()).forEach(i -> al.add(null));

        fieldDescriptions.keySet().forEach(
                k -> al.set(
                        fieldIndexes.get(k),
                        Types.cast(
                                fields.get(k),
                                fieldDescriptions.get(k)
                        )
                )
        );

        var row = new Row();
        row.row = al;

        BlockManager.getInstance().appendElement(
                databaseName,
                tableName,
                getBlocksPointer(),
                row
        );
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

    /*
    на вход нужно подать предобработанный лимит,офсет,where
    то есть, если не было указано лимита, то он должен быть равен Integer.MAX_VALUE,
    если не было указано офсета, то он должен быть равен 0,
    если не было указано where, то он должен быть равен null
    */
    public Selection select(Vector<String> fieldNames, int limit, int offset, String where) throws Exception {
        var fieldIndexes = fieldNames.stream().map(this::getFieldIndex).collect(Collectors.toList());
        var whereExpression = where(where, "r");

        var js = ScriptManager.scriptEngineManager.getEngineByName("js");

        var bei = BlockManager.getInstance().getBlockElementIterator(getBlocksPointer(), Row.class);
        bei.setElement(new Row(new ArrayList<>(fieldDescriptions.values())));
        IntStream.range(0, offset).forEach(i -> bei.next());

        var selection = new Selection(fieldNames, new Vector<>());

        for (int i = 0; i < limit && bei.hasNext(); ++i) {
            var row = bei.next();
            if (!row.isDeleted()) {
                js.put("r", row.row);
                if (where == null || ((boolean) js.eval(whereExpression.getValueForEval()))) {
                    var v = new Vector<>();
                    fieldIndexes.forEach(j -> v.add(row.row.get(j)));
                    selection.add(v);
                }
            }
        }

        return selection;
    }

    /*public void delete(String where) throws Exception {
        if (where == null) {
            var bi = BlockManager.getInstance().getBlockIterator(getBlocksPointer());

        }

        var whereExpression = where(where, "r");

        var js = ScriptManager.scriptEngineManager.getEngineByName("js");

        var bei = BlockManager.getInstance().getBlockElementIterator(getBlocksPointer(), Row.class);
        bei.setElement(new Row(new ArrayList<>(fieldDescriptions.values())));

        while (bei.hasNext()) {
            var row = bei.next();// FIXME: 28.12.2018 clone???
            if (!row.isDeleted()) {
                js.put("r", row.row);
                if ((boolean) js.eval(whereExpression.getValueForEval())) {
                    row.makeDeleted();
                    bei.write(row);
                    //bei.delete(databaseName, tableName, getBlocksPointer());
                }
            }
        }
    }*/

    public void update(Map<String, String> set, String where) throws Exception {
        set.keySet().forEach(this::throwIfNoField);

        var whereExpression = where(where, "r");
        set.replaceAll((fn, e) -> new Expression(e, whereExpression.getFields()).getValueForEval());

        var js = ScriptManager.scriptEngineManager.getEngineByName("js");

        var bei = BlockManager.getInstance().getBlockElementIterator(getBlocksPointer(), Row.class);
        bei.setElement(new Row(new ArrayList<>(fieldDescriptions.values())));

        while (bei.hasNext()) {
            var row = bei.next();
            if (!row.isDeleted()) {
                var tmpRow = row.clone();
                js.put("r", row.row);
                if (where == null || ((boolean) js.eval(whereExpression.getValueForEval()))) {
                    for (var entry : set.entrySet())
                        tmpRow.row.set(
                                fieldIndexes.get(entry.getKey()),
                                Types.cast(
                                        js.eval(entry.getValue()).toString(),
                                        fieldDescriptions.get(entry.getKey())
                                )
                        );

                    bei.write(tmpRow);
                }
            }
        }
    }

    @Override
    public String toString() {
        return
                fieldDescriptions.entrySet().stream()
                        .map(e -> e.getKey() + " " + e.getValue())
                        .collect(Collectors.joining(",\n\t", "create table " + tableName + "(\n\t", "\n)"));
    }
}