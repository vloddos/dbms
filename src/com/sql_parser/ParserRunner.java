package com.sql_parser;

import com.sql_parser.grammar.SqlParser;

import java.io.FileReader;

public final class ParserRunner {

    private static SqlParser parser;

    public static void main(String[] args) throws Exception {
        parser = new SqlParser(new FileReader("src/test/java/resources/TestSandbox.txt"));
        parser.parse();

        /*Token token = parser.parseExitCommand();
        System.out.println("==========I COMMAND: " + token.image + "============");

        //TODO: output "SHOW CREATE TABLE table_name;" result

        for (TableStruct table : tableList) {
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

    /*private void parseExitCommand() throws FileNotFoundException, ParseException {
        parser = new SqlParser(new FileReader("src/test/java/resources/TestSandbox.txt"));
        tableList = parser.initParser();
    }*/
}
