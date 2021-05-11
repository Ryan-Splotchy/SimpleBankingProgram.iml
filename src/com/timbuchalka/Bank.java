package com.timbuchalka;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private String name;
    private static List<Branch> branches = new ArrayList<>();

    public Bank(String name) {
        this.name = name;
    }

    public static  List<Branch> getBranches() {
        return branches;
    }

    public String getName() {
        return name;
    }

    private Branch findBranch(String name) {
        for (Branch branch: branches) {
            if (branch.getName().equals(name)) {
                return branch;
            }
        }

        return null;
    }

    public boolean addBranches(String name) {
       Branch branch = findBranch(name);
       if(branch != null) {
           System.out.println("Branch already exists");
           return false;
       }else {
           System.out.println("Added branch");
           return this.branches.add(new Branch(name));
       }
    }

    public boolean addCustomerToBranch(String branchName, String customerName, int accountNumber, double amount) {
        Branch branch = findBranch(branchName);

        if(branch == null) {
            System.out.println("There is no existing branch with specified name");
            return false;
        }else {
            branch.addCustomer(customerName,accountNumber,amount);
            System.out.println("Added customer to " + branchName);
            return true;
        }
    }

    public boolean removeCustomerFromBranch(String branchName, int accountNumber) {
        Branch branch = findBranch(branchName);
        if(branch == null) {
            System.out.println("Branch does not exist");
            return true;
        }else {
            branch.removeCustomer(accountNumber);
            System.out.println("Removed customer " + branch.findAccount(accountNumber).getName() + " from branch: " + branchName);
            return true;
        }
    }



    public boolean removeBranch(String name) {
        Branch branch = findBranch(name);

        if(branch == null) {
            System.out.println("No existing branch of current name to remove");
            return false;
        }else {
            this.branches.remove(branch);
            System.out.println(branch.getName() + " removed");
            return true;
        }
    }

    public void printBranches() {
        for(Branch branch : branches) {
            System.out.println(branch.getName());
        }
    }
}
