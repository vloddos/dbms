package com.dbms;

import com.dbms.grammar.ArgsGuard;
import com.dbms.grammar.SqlParser;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class SelectFromTable extends AbstractTest {

    @Test
    public void testShouldReturnTableNameAndFieldsAndInsertableValues() throws Exception {
        SqlParser parser = new SqlParser(new FileReader("src/test/java/resources/select_from_table_test/SelectFromTable.txt"));

        FileWriter actual = new FileWriter(new File("src/test/java/resources/select_from_table_test/Actual.txt"));

        ArgsGuard args =  parser.parseSelectFromTable();

        writeEntityName(actual, args);
        writeInsertableColumns(actual, args);
        writeLimitAndOffset(actual, args);

        actual.close();

        assertFiles("select_from_table_test");
    }
}
