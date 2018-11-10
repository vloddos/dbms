package com.dbms;

import com.dbms.grammar.SqlParser;

import java.io.FileReader;

public final class DBRunner {

    public static void main(String[] args) throws Exception {
        
        SqlParser parser = new SqlParser(new FileReader("src/test/java/resources/TestSandbox.txt"));
        parser.parse();

        /*for (TableStruct table : tableList) {
            System.out.println("Table Name: " + table.TableName);

            for (Map.Entry<String, TypeDescription> entry : table.Variables.entrySet()) {
                System.out.print("Field name: " + entry.getKey() + " Data Type: " + entry.getValue().getTypeName());

                if (entry.getValue().getTypeLength() != null)
                    System.out.println(" Length: " + entry.getValue().getTypeLength());
                else
                    System.out.println(" Length: <not specified>");
            }

            System.out.println("--------------------------");
        }*/
    }
}
