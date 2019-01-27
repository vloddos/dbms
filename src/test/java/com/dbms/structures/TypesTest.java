package com.dbms.structures;

import org.junit.Assert;
import org.junit.Test;

public class TypesTest {

    @Test
    public void testShouldReturnTypeName() {
        Assert.assertTrue(Types.haveType("varchar"));
    }

    @Test
    public void testShouldReturnNullCauseArgumentEqualsNull() {
        Assert.assertNull(Types.cast(null, new Type("bool")));
    }

    //FIXME(RoyalStorm): rename test
    @Test
    public void testShouldReturnIDK() {
        Assert.assertEquals("bool", Types.cast("bool", new Type("varchar", 50)).toString());
    }
}
