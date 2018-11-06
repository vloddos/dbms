package dbms;

import java.io.Serializable;
import java.util.Map;

public class TableHeader implements Serializable {

    private static final long serialVersionUID = 1100771866086242892L;

    public String name;
    public Map<String, Type> fields;

    public TableHeader(String name, Map<String, Type> fields) {
        this.name = name;
        this.fields = fields;
    }
}