package com.example.banca2;

public class TransferRequest {
    private int from_account_id;
    private int to_account_id;
    private double amount;
    private String description;

    public TransferRequest(int fromAccountId, int toAccountId, double amount, String description) {
        this.from_account_id = fromAccountId;
        this.to_account_id = toAccountId;
        this.amount = amount;
        this.description = description;
    }

    // Getters y setters si es necesario
}
