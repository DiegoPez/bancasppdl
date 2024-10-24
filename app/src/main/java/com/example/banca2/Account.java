package com.example.banca2;

public class Account {
    private int account_id;
    private String account_type;
    private double balance;  // Cambia a double para poder manejar los decimales.

    // Constructor
    public Account(int account_id, String account_type, double balance) {
        this.account_id = account_id;
        this.account_type = account_type;
        this.balance = balance;
    }

    // Getters
    public int getAccountId() {
        return account_id;
    }

    public String getAccountType() {
        return account_type;
    }

    public double getBalance() {
        return balance;
    }
}
