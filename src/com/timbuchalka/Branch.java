package com.timbuchalka;

import java.util.ArrayList;
import java.util.List;

public class Branch implements IBranchFunctions{
    private String name;
    private List<Account> accounts = new ArrayList<>();

    public Branch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public boolean addCustomer(String name, int accountNum, double amount) {
        for(Account account: accounts) {
            if(account.getAccountNum() == accountNum) {
                System.out.println("Cannot add account as another account exists with provided account number");
                return false;
            }
        }
        return this.accounts.add(new Account(name, accountNum, amount));
    }

    @Override
    public boolean removeCustomer(int accountNum) {
        for (Account account: accounts) {
            if (account.getAccountNum() == accountNum) {
                this.accounts.remove(account);
                System.out.println("Account " + account.getName() + " removed");
                return true;
            }
        }
        System.out.println("Failed to remove account");
        return false;
    }

    @Override
    public void addTransaction(int accountNum, double amount) {
        for (Account account: accounts) {
            if(account.getAccountNum() != accountNum) {
                System.out.println("No existing account to update");
            }else {
                account.addTransaction(amount);
            }
        }
    }

    public void deposit(int accountNum, double amount) {
        String accountHiddenNum = String.valueOf(accountNum);
        for(Account account: accounts) {
            if (account.getAccountNum() != accountNum) {
                System.out.println("No matching account to update");
            }else {
                if(amount > 0) {
                    account.addToBalance(amount);
                    account.addTransaction(amount);
                    System.out.println("R" + amount + " added to account with name: " + account.getName() + "\n" + "Item you sold to get the amount you deposited: " + shaylinsRandomizer() + "\n" + "With MasterCard ending with *****" + accountHiddenNum.substring(3, accountHiddenNum.length() -1));
                    System.out.println("Balance is now: " + "R" + account.getBalance());
                }else {
                    System.out.println("Cannot deposit negative amounts");
                }
            }
        }
    }

    public void withdraw(int accountNum, double amount) {
        String accountHiddenNum = String.valueOf(accountNum);
        for(Account account: accounts) {
            if(account.getAccountNum() != accountNum) {
                System.out.println("No matching account to update");
            }else {
                double negativeAmount = -amount;
                if(negativeAmount < 0) {
                    account.addToBalance(negativeAmount);
                    account.addTransaction(negativeAmount);
                    System.out.println("R" + amount + " withdrawn from account with name " + account.getName() + "\n" + "Item you Bought: " + shaylinsRandomizer() + "\n" + "With MasterCard ending with *****" + accountHiddenNum.substring(3, accountHiddenNum.length() -1));
                    System.out.println("Balance is now: " + "R" + account.getBalance());
                }else {
                    System.out.println("Cannot withdraw a non-negative amount");
                }
            }
        }
    }

    public void printAccounts() {
        for (Account account: accounts) {
            System.out.println("Name: " + account.getName() + " balance: " + "R" + account.getBalance() + " Account number: " + account.getAccountNum() + " transactions: " + account.getTransactions());
        }
    }

    protected Account findAccount(int accountNumber) {
        for (Account account: accounts) {
            if (account.getAccountNum() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public static String shaylinsRandomizer() {
        String[] randomObjects = {"Dildo", "Pebble", "Toaster", "Mustang", "Toothpick", "Almond", "Fire Lighters", "Weed", "Beer", "Laptop"};
        int random = (int) (Math.random() *10);
        return randomObjects[random];
    }

    public void printTransactions(int accountNum) {
        Account account = findAccount(accountNum);

        if(account == null) {
            System.out.println("No matching account to print transactions");
        }else {
            System.out.println(account.getTransactions());
        }
    }

//    private boolean addToBalance(int accountNum, double amount) {
//        Account account = findAccount(accountNum);
//        if(account == null) {
//            System.out.println("No matching account");
//            return false;
//        }else {
//            account.getBalance() += amount;
//            return result;
//        }
//    }

}
