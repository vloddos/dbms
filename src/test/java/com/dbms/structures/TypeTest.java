package com.dbms.structures;

import org.junit.Assert;
import org.junit.Test;

public class TypeTest {

    private Type INT = new Type("int");
    private Type VARCHAR = new Type("varchar", 50);
    private Type DOUBLE = new Type("double", "20");

    @Test
    public void testShouldSetLengthAndGetLength() {
        INT.setLength(30);

        Assert.assertEquals(30, INT.getLength());
    }

    @Test
    public void testShouldSetNameAndGetName() {
        DOUBLE.setName("CHAR");

        Assert.assertEquals("CHAR", DOUBLE.getName());
    }

    @Test
    public void testShouldReturnStringFormatWithLength() {
        Assert.assertEquals("varchar(50)", VARCHAR.toString());
    }

    @Test
    public void testShouldReturnStringFormatWithoutLength() {
        Assert.assertEquals("int", INT.toString());
    }
}
