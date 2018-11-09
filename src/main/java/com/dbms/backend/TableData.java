package com.dbms.backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class TableData implements Serializable {//нужна ли вообще???

    private static final long serialVersionUID = -1172671911349943558L;

    public Map<String, ArrayList> fields = new LinkedHashMap<>();//field values
}