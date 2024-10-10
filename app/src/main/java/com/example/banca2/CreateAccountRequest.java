package com.example.banca2;

public class CreateAccountRequest {
    private String account_type;
    private int balance;

    public CreateAccountRequest(String account_type, int balance) {
        this.account_type = account_type;
        this.balance = balance;
    }

    public String getAccountType() {
        return account_type;
    }

    public void setAccountType(String account_type) {
        this.account_type = account_type;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
