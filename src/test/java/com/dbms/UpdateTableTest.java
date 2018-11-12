package com.dbms;

import com.dbms.grammar.ArgsGuard;
import com.dbms.grammar.SqlParser;
import junitx.framework.FileAssert;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class UpdateTableTest {

    @Test
    public void testShouldReturnTableNameAndUpdatableValuesAndPredicats() throws Exception {
        SqlParser parser = new SqlParser(new FileReader("src/test/java/resources/update_table_test/UpdateTable.txt"));
        FileWriter actual = new FileWriter(new File("src/test/java/resources/update_table_test/Actual.txt"));
        ArgsGuard args = new ArgsGuard();

        args =  parser.parseUpdate();

        actual.write(args.getName() + "\n");

        for (int i = 0; i < args.getUpdatableColumns().size(); i++) {
            actual.write(args.getUpdatableColumns().get(i) + "\n");
            actual.write(args.getUpdatableValues().get(i) + "\n");
        }

        for (int i = 0; i < args.getCompareValues().size(); i++) {
            actual.write(args.getCompareColumns().get(i) + "\n");
            actual.write(args.getCompareSigns().get(i) + "\n");
            actual.write(args.getCompareValues().get(i) + "\n");

            if (i < args.getCompareValues().size() - 1)
                actual.write(args.getComparePredicats().get(i) + "\n");
        }

        actual.close();

        FileAssert.assertEquals(
                new File("src/test/java/resources/update_table_test/Expected.txt"),
                new File("src/test/java/resources/update_table_test/Actual.txt")
        );
    }
}
