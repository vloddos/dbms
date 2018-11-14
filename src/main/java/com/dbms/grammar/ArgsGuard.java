package com.dbms.grammar;

import java.util.ArrayList;

/**
 * Special class for using in tests class only
 * So, for example, method parseInsertIntoTable() must return next params:
 *     -String name.image
 *     -String command.image
 *     -ArrayList<String> insertableColumns
 *     -ArrayList<String> insertableValues
 * One "return" returns (sorry :)) one param, but for testing need all
 */
public final class ArgsGuard {
    //For "INSERT" conditions
    private ArrayList<String> insertableColumns = new ArrayList<String>();
    private ArrayList<String> insertableValues = new ArrayList<String>();

    //For "WHERE" conditions
    private ArrayList<String> compareColumns = new ArrayList<String>();
    private ArrayList<String> compareSigns = new ArrayList<String>();
    private ArrayList<String> compareValues = new ArrayList<String>();
    private ArrayList<String> comparePredicats = new ArrayList<String>();

    //For "UPDATE" conditions
    private ArrayList<String> updatableColumns = new ArrayList<String>();
    private ArrayList<String> updatableValues = new ArrayList<String>();

    //Table name
    private String name;

    //For "SELECT"
    private int limit = -1;
    private int offset = -1;

    public ArrayList<String> getInsertableColumns() {
        return insertableColumns;
    }

    public void setInsertableColumns(ArrayList<String> insertableColumns) {
        this.insertableColumns = insertableColumns;
    }

    public ArrayList<String> getInsertableValues() {
        return insertableValues;
    }
    
    public String getInsertableValue(int i) {
        return insertableValues.get(i);
    }

    public void setInsertableValues(ArrayList<String> insertableValues) {
        this.insertableValues = insertableValues;
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

    public ArrayList<String> getCompareColumns() {
        return compareColumns;
    }

    public void setCompareColumns(ArrayList<String> compareColumns) {
        this.compareColumns = compareColumns;
    }

    public ArrayList<String> getCompareSigns() {
        return compareSigns;
    }

    public void setCompareSigns(ArrayList<String> compareSigns) {
        this.compareSigns = compareSigns;
    }

    public ArrayList<String> getCompareValues() {
        return compareValues;
    }

    public void setCompareValues(ArrayList<String> compareValues) {
        this.compareValues = compareValues;
    }

    public ArrayList<String> getComparePredicats() {
        return comparePredicats;
    }

    public void setComparePredicats(ArrayList<String> comparePredicats) {
        this.comparePredicats = comparePredicats;
    }

    public ArrayList<String> getUpdatableColumns() {
        return updatableColumns;
    }

    public void setUpdatableColumns(ArrayList<String> updatableColumns) {
        this.updatableColumns = updatableColumns;
    }

    public ArrayList<String> getUpdatableValues() {
        return updatableValues;
    }

    public void setUpdatableValues(ArrayList<String> updatableValues) {
        this.updatableValues = updatableValues;
    }
}
