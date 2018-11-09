package com.dbms;

import com.dbms.grammar.SqlParser;
import org.junit.Test;

import java.io.FileReader;

public class ThrowExceptionTest {

    @Test(expected = Exception.class)
    public void testShouldThrowExceptionCauseBadDataType() throws Exception {
        new SqlParser(new FileReader("src/test/java/resources/throw_exception_test/ThrowException.txt")).parse();
    }
}
