package com.example.banca2;

public class TransactionRequest {
    private int account_id;
    private String transaction_type;
    private double amount;
    private String description;

    public TransactionRequest(int account_id, String transaction_type, double amount, String description) {
        this.account_id = account_id;
        this.transaction_type = transaction_type;
        this.amount = amount;
        this.description = description;
    }
    // Getters y setters
}
