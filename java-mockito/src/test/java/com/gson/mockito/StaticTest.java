package com.gson.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


public class StaticTest {

    @Test
    public void givenStaticMethodWithNoArgs_whenMocked_thenReturnsMockSuccessfully() {
        Assert.assertEquals(StaticUtils.name(),"Baeldung");

        try (MockedStatic<StaticUtils> utilities = Mockito.mockStatic(StaticUtils.class)) {
            utilities.when(StaticUtils::name).thenReturn("Eugen");
            Assert.assertEquals(StaticUtils.name(),"Eugen");
        }

        Assert.assertEquals(StaticUtils.name(), "Baeldung");
    }
}
