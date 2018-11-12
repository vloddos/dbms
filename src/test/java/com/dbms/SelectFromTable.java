package com.dbms;

import com.dbms.grammar.ArgsGuard;
import com.dbms.grammar.SqlParser;
import junitx.framework.FileAssert;
import org.junit.Test;

import java.io.*;

public class SelectFromTable {

    @Test
    public void testShouldReturnTableNameAndFieldsAndInsertableValues() throws Exception {
        SqlParser parser = new SqlParser(new FileReader("src/test/java/resources/select_from_table_test/SelectFromTable.txt"));
        FileWriter actual = new FileWriter(new File("src/test/java/resources/select_from_table_test/Actual.txt"));
        ArgsGuard args = new ArgsGuard();

        args =  parser.parseSelectFromTable();

        actual.write(args.getName() + "\n");

        for (int i = 0; i < args.getColumns().size() - 1; i++)
            actual.write(args.getColumns().get(i) + ", ");

        actual.write(args.getColumns().get(args.getColumns().size() - 1) + "\n");
        actual.write(String.valueOf(args.getLimit()) + "\n");
        actual.write(String.valueOf(args.getOffset()));

        actual.close();

        FileAssert.assertEquals(
                new File("src/test/java/resources/select_from_table_test/Expected.txt"),
                new File("src/test/java/resources/select_from_table_test/Actual.txt")
        );
    }
}
