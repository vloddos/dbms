package com.dbms;

import com.dbms.grammar.SqlParser;
import junitx.framework.FileAssert;

import java.io.File;
import java.io.FileWriter;

public abstract class Test {

    private SqlParser parser;

    private FileWriter actual;

    public void assertFiles(String expectedPath, String actualPath) {
        FileAssert.assertEquals(
            new File(expectedPath),
            new File(actualPath)
        );
    }
}
