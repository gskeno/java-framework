package com.gson.logback.mdc;

import org.junit.Test;

import java.rmi.RemoteException;
import java.util.Arrays;

public class NumberCruncherTest {
    @Test
    public void test() throws RemoteException {
        NumberCruncherServer server = new NumberCruncherServer();
        int[] factor = server.factor(15);
        System.out.println(Arrays.toString(factor));

    }
}
