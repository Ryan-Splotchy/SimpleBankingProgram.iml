package com.timbuchalka;

public interface IBranchFunctions {
    boolean addCustomer(String name, int accountNum, double amount);
    boolean removeCustomer(int accountNum);
    void addTransaction(int accountNum, double amount);
}
