package com.timbuchalka;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String name;
    private int accountNum;
    private List<Double> transactions;
    private double balance;

    public Account(String name, int accountNum, double amount) {
        this.name = name;

//        int digits = 0;
//
//        while (accountNum != 0) {
//            int last = accountNum % 10;
//            digits++;
//            accountNum /= 10;
//        }

//        if(digits > 8) {
//            System.out.println("Account number too large, please enter a number between 0 and 9 digits");
//        }else {
//            this.accountNum = accountNum;
//        }
        this.accountNum = accountNum;
        this.transactions = new ArrayList<>();
        addTransaction(amount);
        initialDeposit(amount);
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public List<Double> getTransactions() {
        return transactions;
    }

    public void addTransaction(double amount) {
        this.transactions.add(amount);
    }

    private void initialDeposit(double amount) {
        this.balance = amount;
    }

    public void addToBalance(double amount) {
        this.balance += amount;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        } else {
            System.out.println("Cannot deposit negative amounts");
        }
    }

    public void withdraw(double amount) {

        double negativeAmount = -amount;
        if(negativeAmount < 0) {
            this.balance += negativeAmount;
            addTransaction(negativeAmount);
        }else {
            System.out.println("Cannot withdraw non-negative amounts");
        }


    }
}
