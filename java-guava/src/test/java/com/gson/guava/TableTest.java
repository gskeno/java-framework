package com.gson.guava;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import org.junit.Assert;
import org.junit.Test;

public class TableTest extends Assert {
    @Test
    public void test(){
        Table<String, String, String> table = HashBasedTable.create();
        for (int i = 0; i < 5; i++) {
            table.put("r" + i, "c" + i, "v" + i);
        }
        assertTrue(table.rowKeySet().containsAll(Sets.newHashSet("r0", "r1","r2","r3","r4")));
        assertTrue(table.columnKeySet().containsAll(Sets.newHashSet("c0", "c1","c2","c3","c4")));
        assertTrue(table.values().containsAll(Sets.newHashSet("v0", "v1","v2","v3","v4")));
    }
}
