package com.bank.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankService extends Remote {
    void deposit(String accountNumber,double amount) throws RemoteException;
    void withdraw(String accountNumber,double amount) throws RemoteException;
    double checkBalance(String accountNumber) throws RemoteException;
}
