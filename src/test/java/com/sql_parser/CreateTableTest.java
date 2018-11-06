/*
package com.sql_parser;

import com.sql_parser.grammar.ParseException;
import com.sql_parser.grammar.SqlParser;
import com.sql_parser.grammar.TableStruct;
import dbms.TypeDescription;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

public class CreateTableTest {

    private SqlParser sqlParser;
    private ArrayList<TableStruct> tableList;
    private int tableIndex = 0, fieldIndex = 0;

    @Test
    public void CreateTableCapitalCamelCaseTest() throws FileNotFoundException, ParseException {
        sqlParser = new SqlParser(new FileReader("src/test/java/resources/create_table_test/CreateTableCapitalCamelCase.txt"));
        tableList = sqlParser.initParser();

        for (TableStruct table : tableList) {
            Assert.assertEquals("Student", table.TableName);

            for (Map.Entry<String, TypeDescription> entry : table.Variables.entrySet()) {
                //FIXME: delete switch and write normal test, idk how better to do
                switch (fieldIndex) {
                    case 0:
                        Assert.assertEquals("student_id", entry.getKey());
                        Assert.assertEquals("LONG", entry.getValue().getTypeName());
                        Assert.assertNull(entry.getValue().getTypeLength());
                        break;
                    case 1:
                        Assert.assertEquals("class", entry.getKey());
                        Assert.assertEquals("VARCHAR", entry.getValue().getTypeName());
                        Assert.assertEquals("10", entry.getValue().getTypeLength().toString());
                        break;
                    case 2:
                        Assert.assertEquals("age", entry.getKey());
                        Assert.assertEquals("INT", entry.getValue().getTypeName());
                        Assert.assertNull(entry.getValue().getTypeLength());
                        break;
                    case 3:
                        Assert.assertEquals("first_name", entry.getKey());
                        Assert.assertEquals("VARCHAR", entry.getValue().getTypeName());
                        Assert.assertEquals("25", entry.getValue().getTypeLength().toString());
                        break;
                }
                fieldIndex++;
            }
        }
    }

    @Test
    public void CreateManyTablesTest() throws FileNotFoundException, ParseException {
        sqlParser = new SqlParser(new FileReader("src/test/java/resources/create_table_test/CreateManyTables.txt"));
        tableList = sqlParser.initParser();

        for (TableStruct table : tableList) {
            //FIXME: delete switch and write normal test, idk how better to do
            switch (tableIndex) {
                case 0:
                    Assert.assertEquals("Book", table.TableName);
                    break;
                case 1:
                    Assert.assertEquals("Author", table.TableName);
                    break;
                case 2:
                    Assert.assertEquals("Publisher", table.TableName);
                    break;
            }
            tableIndex++;
            //TODO: continue check all table fields
        }
    }
}
*/
