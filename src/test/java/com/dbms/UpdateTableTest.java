package com.dbms;

import com.dbms.grammar.ArgsGuard;
import com.dbms.grammar.SqlParser;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class UpdateTableTest extends AbstractTest {

    @Test
    public void testShouldReturnTableNameAndUpdatableValuesAndPredicats() throws Exception {
        SqlParser parser = new SqlParser(new FileReader("src/test/java/resources/update_table_test/UpdateTable.txt"));
        FileWriter actual = new FileWriter(new File("src/test/java/resources/update_table_test/Actual.txt"));

        ArgsGuard args =  parser.parseUpdate();

        writeEntityName(actual, args);
        writeUpdatableColumns(actual, args);

        for (int i = 0; i < args.getCompareValues().size(); i++) {
            actual.write(args.getCompareValues().get(i) + "\n");

            //if (i < args.getCompareValues().size() - 1)
            //    actual.write(args.getComparePredicats().get(i) + "\n");
        }

        actual.close();

        assertFiles("update_table_test");
    }
}
