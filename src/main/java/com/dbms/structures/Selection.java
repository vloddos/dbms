package com.dbms.structures;

import dnl.utils.text.table.TextTable;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

public class Selection {

    private Vector<String> columnNames;
    private Vector<Vector<?>> data;

    private TextTable textTable;

    public Selection(Vector<String> columnNames, Vector<Vector<?>> data) {
        this.columnNames = columnNames;
        this.data = data;
        textTable = new TextTable(new DefaultTableModel(data, columnNames));
    }

    public void add(Vector<?> vector) {
        data.add(vector);
    }

    public void add(ArrayList<?> arrayList) {
        add(new Vector<>(arrayList));
    }

    public void print() {
        textTable.printTable();
    }
}