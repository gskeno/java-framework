package com.gson.logback.mdc;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NumberCruncher extends Remote {

    /**
     * 整数进行因子分解
     * @param number
     * @return
     * @throws RemoteException
     */
    int[] factor(int number) throws RemoteException;
}
