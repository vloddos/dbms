package com.dbms.structs;

import org.junit.Assert;
import org.junit.Test;

public class TypeDescriptionTest {

    TypeDescription INT = new TypeDescription("int");
    TypeDescription VARCHAR = new TypeDescription("varchar", 50);
    TypeDescription DOUBLE = new TypeDescription("double", "20");

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
