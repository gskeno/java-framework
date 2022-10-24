package com.gson.guava.basic.utility;

import com.google.common.base.Preconditions;
import org.junit.Test;

public class PreconditionsTest {

    @Test
    public void test() {
        Preconditions.checkArgument(1 > 2, "1 > 2 is false");
        Preconditions.checkNotNull(null, "info is null");
    }
}
