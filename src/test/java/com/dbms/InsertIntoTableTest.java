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
    public void testShouldReturnTableNameAndFieldsAndInsertableValues() throws Exception {
        SqlParser parser = new SqlParser(new FileReader("src/test/java/resources/insert_into_table_test/InsertIntoTable.txt"));
        FileWriter actual = new FileWriter(new File("src/test/java/resources/insert_into_table_test/Actual.txt"));
        ArgsGuard args = new ArgsGuard();

        args =  parser.parseInsertIntoTable();

        actual.write(args.getName() + "\n");

        //FIXME: test won't works cause each string contains extra chars, but all fields are corrects, see Expected.txt & Actual.txt
        for(String column: args.getColumns())
            actual.write(column + ", ");

        actual.write("\n");

        for (String value: args.getInsertableValues()) {
            actual.write(value + ", ");
        }

        actual.close();

        FileAssert.assertEquals(
                new File("src/test/java/resources/insert_into_table_test/Expected.txt"),
                new File("src/test/java/resources/insert_into_table_test/Actual.txt")
        );
    }
}
