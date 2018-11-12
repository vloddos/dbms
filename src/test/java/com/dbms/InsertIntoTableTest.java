package com.dbms;

import com.dbms.grammar.ArgsGuard;
import com.dbms.grammar.SqlParser;
import junitx.framework.FileAssert;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class InsertIntoTableTest {

    @Test
    public void testShouldReturnTableNameAndColumnsAndInsertableValues() throws Exception {
        SqlParser parser = new SqlParser(new FileReader("src/test/java/resources/insert_into_table_test/InsertIntoTable.txt"));
        FileWriter actual = new FileWriter(new File("src/test/java/resources/insert_into_table_test/Actual.txt"));
        ArgsGuard args = new ArgsGuard();

        args =  parser.parseInsertIntoTable();

        actual.write(args.getName() + "\n");

        for (int i = 0; i < args.getColumns().size() - 1; i++)
            actual.write(args.getColumns().get(i) + ", ");

        actual.write(args.getColumns().get(args.getColumns().size() - 1) + "\n");


        for (int i = 0; i < args.getInsertableValues().size() - 1; i++)
            actual.write(args.getInsertableValue(i) + ", ");

        actual.write(args.getInsertableValue(args.getInsertableValues().size() - 1));

        actual.close();

        FileAssert.assertEquals(
                new File("src/test/java/resources/insert_into_table_test/Expected.txt"),
                new File("src/test/java/resources/insert_into_table_test/Actual.txt")
        );
    }
}
