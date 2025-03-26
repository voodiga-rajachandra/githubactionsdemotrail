package com.bank;

import com.bank.common.BankService;
import com.bank.services.BankServiceImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServerBankAPI {
    public static void main(String[] args) {

        try {
            BankService bankService = new BankServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("BankService",bankService);
            System.out.println("Bank Service is ready.");


        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            System.err.println("Bank Server exception: " + e.getMessage());
            e.getStackTrace();

        }


        System.out.println("Hello, server!");
    }
}