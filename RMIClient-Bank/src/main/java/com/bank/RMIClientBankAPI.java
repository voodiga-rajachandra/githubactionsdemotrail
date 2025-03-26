package com.bank;

import com.bank.common.BankService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Scanner;

public class RMIClientBankAPI {
    public static void main(String[] args) {
        System.out.println("RMI Server Bank");

        //region first
        BankService bankService = null;
        try {
      bankService   = (BankService) Naming.lookup("rmi://localhost/BankService");
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            System.out.println("Bank Client exception" + " "  + Arrays.toString(e.getStackTrace()));
        }

        Scanner scanner = new Scanner(System.in);
while (true){

    System.out.println("Choose an option:");
    System.out.println("1. Deposit");
    System.out.println("2. Withdraw");
    System.out.println("3. Check Balance");
    System.out.println("4. Exit");

    try {
        int choice = scanner.nextInt();
        scanner.nextLine();
        String accountNumber = "";
        switch (choice){

            case 1:
                System.out.println("Enter account number:");
              accountNumber  = scanner.nextLine();
                System.out.println("Enter amount to deposit:");
                double depositAmount = scanner.nextDouble();
                try {
//                    assert bankService != null;
                    if (bankService == null){
                        System.out.println("Bank service is not available.");
continue;
                    }
                    bankService.deposit(accountNumber,depositAmount);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                break;

            case 2:
                System.out.print("Enter account number: ");
                accountNumber = scanner.nextLine();
                System.out.print("Enter amount to withdraw: ");
                double withdrawAmount = scanner.nextDouble();
                try {
               //     assert bankService != null;
                    if (bankService == null){
                        System.out.println("Bank service is not available.");
                        continue;
                    }
                    bankService.withdraw(accountNumber,withdrawAmount);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                break;

            case 3:
                System.out.print("Enter account number: ");
                accountNumber = scanner.nextLine();
                try {
                 //   assert bankService != null;
                    if (bankService == null){
                        System.out.println("Bank service is not available.");
                        continue;
                    }
                    double balance = bankService.checkBalance(accountNumber);
                    System.out.println("The balance is :" + " " + balance);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                break;

            case 4:
                System.out.println("For Exiting Please Enter 4 number (Numeric)");
                System.exit(0);

            default:
                System.out.println("Invalid choice, try again.");


        }
    }catch (Exception e){
       e.printStackTrace();
    }finally {
        System.out.println("Operation completed.");
        System.out.println("RMI Project bank client!!");
    }
    }

//endregion






    }
}