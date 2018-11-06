import dbms.*;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        //DataBases.createDataBase("huy");
        //DataBases.createDataBase("pizda");
        //DataBases.useDataBase("huy");
        /*Map<String, Type> map = new LinkedHashMap<>();
        map.put("field1", new Type("int"));
        map.put("field2", new Type("char"));
        map.put("field3", new Type("double"));
        DataBases.currentDataBase.createTable(
                new Table(
                        new TableHeader(
                                "table1",
                                map
                        )
                )
        );*/
        //System.out.println(DataBases.currentDataBase.showCreate("table1"));
        //DataBases.exit();
    }
}



