package com.bank.services;

import com.bank.common.BankService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class BankServiceImpl extends UnicastRemoteObject implements BankService {

    private Map<String,Double> accounts;


    public BankServiceImpl() throws RemoteException {
        super();
        this.accounts = new HashMap<>();
        accounts.put("1au",3000.0);
        accounts.put("2au",5000.0);
        accounts.put("3au",8000.0);
        accounts.put("4au",50000.0);
        accounts.put("5au",500000.0);
        accounts.put("6au",5000000.0);
        accounts.put("7au",85000.0);
    }


    @Override
    public void deposit(String accountNumber, double amount) throws RemoteException {

       if (accounts.containsKey(accountNumber)){
           double currentBalance = accounts.get(accountNumber);
           double newBalance = currentBalance + amount;
           accounts.put(accountNumber,newBalance);
           System.out.println("Deposit successful. New balance is : " + " " + newBalance + "  "
                   +  "  for Account :" + "  " + accountNumber );
       }else {
           throw  new RemoteException("Account not found!!!");
       }


    }

    @Override
    public void withdraw(String accountNumber, double amount) throws RemoteException {

        if (accounts.containsKey(accountNumber)){
            double currentBalance = accounts.get(accountNumber);
            if (currentBalance >= amount){
                double newBalance = currentBalance - amount;
                accounts.put(accountNumber,newBalance);
                System.out.println("Withdraw successful. New balance is :" + " " + newBalance +
                        " " + "for account :" + " "  + accountNumber);
            }else {
                throw new RemoteException("The current balance is not sufficient. ");
            }

        }else {
            throw new RemoteException("Account not found!!!");
        }


    }

    @Override
    public double checkBalance(String accountNumber) throws RemoteException {
        if (accounts.containsKey(accountNumber)){
            return accounts.get(accountNumber);
        }else {
            throw new RemoteException("Account not found!!!");
        }
    }
}
