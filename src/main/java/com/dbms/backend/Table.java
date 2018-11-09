package com.dbms.backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Table implements Serializable {

    private static final long serialVersionUID = 7321072388697834267L;

    public TableHeader header;
    public TableData data = new TableData();

    public Table(TableHeader header) {
        this.header = header;
        this.header.fields.forEach((k, v) -> data.fields.put(k, new ArrayList()));
    }

    public void insert(Map<String, String> fields) {
        data.fields.forEach(
                (k, v) ->
                        v.add(
                                Types.cast(
                                        fields.get(k),
                                        header.fields.get(k)
                                )
                        )
        );
    }

    public void test() {
        header.fields.forEach((k, v) -> System.out.println(k + ":" + v));
    }
    //select
}