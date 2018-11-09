package com.dbms;

import com.dbms.grammar.ParseException;
import com.dbms.grammar.SqlParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ExitTest {

    @Test
    public void testShouldReturnCommand() throws FileNotFoundException, ParseException {
        SqlParser parser = new SqlParser(new FileReader("src/test/java/resources/exit_test/ExitFromConsole.txt"));

        Assert.assertEquals("Exit", parser.parseExit().image);
    }
}
