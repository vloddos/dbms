package com.dbms;

import com.dbms.grammar.ArgsGuard;
import com.dbms.grammar.SqlParser;
import junitx.framework.FileAssert;
import org.junit.Test;

import java.io.*;

public class DeleteFromTableTest {

    @Test
    public void testShouldReturnTableNameAndCompareFieldsAndPredicats() throws Exception {
        SqlParser parser = new SqlParser(new FileReader("src/test/java/resources/delete_from_table_test/DeleteFromTable.txt"));
        FileWriter actual = new FileWriter(new File("src/test/java/resources/delete_from_table_test/Actual.txt"));
        ArgsGuard args = new ArgsGuard();

        args =  parser.parseDelete();

        actual.write(args.getName() + "\n");

        for (int i = 0; i < args.getCompareValues().size(); i++) {
            actual.write(args.getCompareColumns().get(i) + "\n");
            actual.write(args.getCompareSigns().get(i) + "\n");
            actual.write(args.getCompareValues().get(i) + "\n");

            if (i < args.getCompareValues().size() - 1)
                actual.write(args.getComparePredicats().get(i) + "\n");
        }

        actual.close();

        FileAssert.assertEquals(
                new File("src/test/java/resources/delete_from_table_test/Expected.txt"),
                new File("src/test/java/resources/delete_from_table_test/Actual.txt")
        );
    }
}
