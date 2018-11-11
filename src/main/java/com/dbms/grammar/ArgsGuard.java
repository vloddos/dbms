package com.dbms.grammar;

import java.util.ArrayList;

/**
 * Special class for using in tests class only
 * So, for example, method parseInsertIntoTable() must return next params:
 *     -String name.image
 *     -String command.image
 *     -ArrayList<String> columns
 *     -ArrayList<String> insertableValues
 * One "return" returns (sorry :)) one param, but for testing need all
 */
public final class ArgsGuard {

    private ArrayList<String> columns = new ArrayList<String>();
    private ArrayList<String> insertableValues = new ArrayList<String>();

    private String command;
    private String name;

    private int limit;
    private int offset;

    public ArrayList<String> getColumns() {
        return columns;
    }
    
    public String getColumn(int i) {
        return columns.get(i);
    }
    
    public int getColumnsLength() {
        return columns.size();
    }

    public void setColumns(ArrayList<String> columns) {
        this.columns = columns;
    }

    public ArrayList<String> getInsertableValues() {
        return insertableValues;
    }
    
    public String getInsertableValue(int i) {
        return insertableValues.get(i);
    }
    
    public int getInsertableValuesLength() {
        return insertableValues.size();
    }

    public void setInsertableValues(ArrayList<String> insertableValues) {
        this.insertableValues = insertableValues;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
