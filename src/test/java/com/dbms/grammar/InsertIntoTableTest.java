package com.dbms.grammar;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class InsertIntoTableTest extends AbstractTest {

    @Test
    public void testShouldReturnTableNameAndColumnsAndInsertableValues() throws Exception {
        SqlParser parser = new SqlParser(new FileReader("src/test/java/resources/insert_into_table_test/InsertIntoTable.txt"));

        FileWriter actual = new FileWriter(new File("src/test/java/resources/insert_into_table_test/Actual.txt"));

        ArgsGuard args =  parser.parseInsertIntoTable();

        writeEntityName(actual, args);
        writeInsertableColumns(actual, args);
        writeInsertableValues(actual, args);

        actual.close();

        assertFiles("insert_into_table_test");
    }
}
