package com.dbms.grammar;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class DeleteFromTableTest extends AbstractTest {

    @Test
    public void testShouldReturnTableNameAndCompareFieldsAndPredicats() throws Exception {
        SqlParser parser = new SqlParser(new FileReader("src/test/java/resources/delete_from_table_test/DeleteFromTable.txt"));
        FileWriter actual = new FileWriter(new File("src/test/java/resources/delete_from_table_test/Actual.txt"));

        ArgsGuard args =  parser.parseDelete();

        writeEntityName(actual, args);

        for (int i = 0; i < args.getCompareValues().size(); i++) {
            //actual.write(args.getCompareColumns().get(i) + "\n");
            //actual.write(args.getCompareSigns().get(i) + "\n");
            actual.write(args.getCompareValues().get(i) + "\n");

            //if (i < args.getCompareValues().size() - 1)
            //    actual.write(args.getComparePredicats().get(i) + "\n");
        }

        actual.close();

        assertFiles("delete_from_table_test");
    }
}