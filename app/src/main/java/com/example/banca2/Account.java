package com.example.banca2;

import com.google.gson.annotations.SerializedName;

public class Account {
    @SerializedName("account_id") // Mapea al nombre del JSON
    private int accountId;

    @SerializedName("account_type") // Mapea al nombre del JSON
    private String accountType;

    private double balance;

    public Account(int accountId, String accountType) {
        this.accountId = accountId;
        this.accountType = accountType;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getAccountType() {
        return accountType;
    }
}
