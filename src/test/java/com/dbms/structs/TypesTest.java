package com.dbms.structs;

import com.dbms.data_types.Varchar;
import org.junit.Assert;
import org.junit.Test;

public class TypesTest {

    @Test
    public void testShouldReturnTypeName() {
        Assert.assertTrue(Types.haveType("varchar"));
    }

    @Test
    public void testShouldReturnNullCauseArgumentEqualsNull() {
        Assert.assertNull(Types.cast(null, new TypeDescription("bool")));
    }

    //FIXME(RoyalStorm): rename test
    @Test
    public void testShouldReturnIDK() {
        Assert.assertEquals("bool", Types.cast("bool", new TypeDescription("varchar", 50)).toString());
    }
}
