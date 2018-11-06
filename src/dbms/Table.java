package dbms;

import java.io.Serializable;

public class Table implements Serializable {

    private static final long serialVersionUID = 7321072388697834267L;

    public TableHeader header;
    //public TableData data;

    public Table(TableHeader header) {
        this.header = header;
    }
    //insert
    //select
}