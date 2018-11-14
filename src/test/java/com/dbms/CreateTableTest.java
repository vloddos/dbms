/*
package com.dbms;

import com.dbms.grammar.ArgsGuard;
import com.dbms.grammar.SqlParser;
import junitx.framework.FileAssert;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class CreateTableTest {

    @Test
    public void testShouldReturnTableNameAndFields() throws Exception {
        SqlParser parser = new SqlParser(new FileReader("src/test/java/resources/create_table_test/CreateTable.txt"));
        FileWriter actual = new FileWriter(new File("src/test/java/resources/create_table_test/Actual.txt"));
        ArgsGuard args = new ArgsGuard();

        args =  parser.parseCreateTable();

        actual.write(args.getName() + "\n");

        for (int i = 0; i < args.getInsertableColumns().size() - 1; i++)
            actual.write(args.getInsertableColumns().get(i) + ", ");

        actual.write(args.getInsertableColumns().get(args.getInsertableColumns().size() - 1) + "\n");


        for (int i = 0; i < args.getInsertableValues().size() - 1; i++)
            actual.write(args.getInsertableValue(i) + ", ");

        actual.write(args.getInsertableValue(args.getInsertableValues().size() - 1));

        actual.close();

        FileAssert.assertEquals(
                new File("src/test/java/resources/create_table_test/Expected.txt"),
                new File("src/test/java/resources/create_table_test/Actual.txt")
        );
    }
}
*/
