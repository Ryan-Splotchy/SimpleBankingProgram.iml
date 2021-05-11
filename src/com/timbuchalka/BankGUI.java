package com.timbuchalka;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BankGUI extends JFrame
{
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 250;
    private static final double INITIAL_BALANCE = 0.00;
    private double result;
    private final JLabel item = new JLabel();
    private JLabel initialLabel;
    private JLabel depositLabel;
    private JLabel withdrawLabel;
    private JTextField depositField;
    private JTextField withdrawField;
    private JButton transactionsButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JLabel resultLabel;
    private JPanel controlPanel;
    private Account account = new Account("Test oh yeah", 12365412, 0);


    public boolean accountFrame()
    {

        resultLabel = new JLabel("New Balance: R" + account.getBalance());



        createTextField();
        createButton();
        createControlPanel();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        return true;
    }

//    public boolean addCustomer(String name, int accountNum, double amount) {
//        for(Account account: accountList) {
//            if(account.getAccountNum() == accountNum) {
//                System.out.println("Cannot add account as another account exists with provided account number");
//                return false;
//            }
//        }
//        return this.accountList.add(new Account(name, accountNum, amount));
//    }


//    public boolean removeCustomer(int accountNum) {
//        for (Account account: accountList) {
//            if (account.getAccountNum() == accountNum) {
//                this.accountList.remove(account);
//                System.out.println("Account " + account.getName() + " removed");
//                return true;
//            }
//        }
//        System.out.println("Failed to remove account");
//        return false;
//    }

//    protected Account findAccount(int accountNumber) {
//        for (Account account: accountList) {
//            if (account.getAccountNum() == accountNumber) {
//                return account;
//            }
//        }
//        return null;
//    }

    private void createTextField()
    {
        final int FIELD_WIDTH = 5;

        initialLabel = new JLabel("Initial Balance £" + INITIAL_BALANCE);
        depositLabel = new JLabel("Deposit: ");
        depositField = new JTextField(FIELD_WIDTH);
        withdrawLabel = new JLabel("Withdraw: ");
        withdrawField = new JTextField(FIELD_WIDTH);
    }

    private void createButton()
    {

        //Create deposit button and assign an action listener
        depositButton = new JButton("Deposit");

        class DepositListener implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                double depositAmount = Double.parseDouble(depositField.getText());
                if(depositAmount == 0) {
                    item.setText("Please specify an amount");
                }else {
                    account.deposit(depositAmount);
                    item.setText("Item you bought: " + Branch.shaylinsRandomizer()); 
                }



                result = account.getBalance();
                resultLabel.setText("New Balance: " + result);
                depositField.setText("0.00");
            }
        }

        ActionListener d = new DepositListener();

        depositButton.addActionListener(d);

        //Implement action listener for withdraw button
        withdrawButton = new JButton("Withdraw");

        class WithdrawListener implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                double withdrawl = Double.parseDouble(withdrawField.getText());
                if(account.getBalance() < withdrawl) {
                    item.setText("Insufficient Funds");
                }else if(withdrawl == 0.00){
                    item.setText("Please specify an amount");
                }else {
                    account.withdraw(withdrawl);
                    item.setText("Item you sold: " + Branch.shaylinsRandomizer());
                }

                result = account.getBalance();
                resultLabel.setText("New Balance: " + result);
                withdrawField.setText("0.00");
            }
        }

        ActionListener w = new WithdrawListener();
        withdrawButton.addActionListener(w);

//        transactionsButton = new JButton("Print Transactions");
//        class TransactionsListener implements ActionListener {
//            @Override
//            public void actionPerformed(ActionEvent event) {
//                item.setText("Account name: " + account.getName() + "\t" + "Account Balance: " + account.getBalance() + "\t" + "Transactions: ") + printTransactions());
//            }
//        }
    }

    private void printTransactions() {
        System.out.println(account.getTransactions());
    }

    private void createControlPanel()
    {

        controlPanel = new JPanel();
        controlPanel.add(initialLabel);
        controlPanel.add(depositLabel);
        controlPanel.add(depositField);
        controlPanel.add(depositButton);
        controlPanel.add(withdrawLabel);
        controlPanel.add(withdrawField);
        controlPanel.add(withdrawButton);
        controlPanel.add(resultLabel);
        controlPanel.add(item);
        add(controlPanel);
    }


    public void deposit(double initialBalance, double amountToBeAdded) {
        if(amountToBeAdded > 0) {
            result = initialBalance + amountToBeAdded;
        }else{
            System.out.println("Cannot deposit negative amounts");
        }
    }

    public void withdraw(double balance, double amountToBeWithdrawn) {
        double negative = -amountToBeWithdrawn;
        if(negative < 0) {
            result = balance += amountToBeWithdrawn;
        }else {
            System.out.println("Cannot withdraw non-negative amounts");
        }
    }



}