package com.example.banca2;

public class Movement {
    private int transaction_id;
    private String transaction_type; // deposit, withdrawal
    private double amount; // Cambiar a double
    private String transaction_date;
    private String description;

    private int transfer_id;
    private int from_account_id;
    private int to_account_id;
    private String transfer_date;

    // Getters y setters
    public int getTransactionId() {
        return transaction_id;
    }

    public void setTransactionId(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTransactionType() {
        return transaction_type;
    }

    public void setTransactionType(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public double getAmount() { // Cambiar a double
        return amount;
    }

    public void setAmount(double amount) { // Cambiar a double
        this.amount = amount;
    }

    public String getTransactionDate() {
        return transaction_date;
    }

    public void setTransactionDate(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public int getTransferId() {
        return transfer_id;
    }

    public void setTransferId(int transfer_id) {
        this.transfer_id = transfer_id;
    }

    public int getFromAccountId() {
        return from_account_id;
    }

    public void setFromAccountId(int from_account_id) {
        this.from_account_id = from_account_id;
    }

    public int getToAccountId() {
        return to_account_id;
    }

    public void setToAccountId(int to_account_id) {
        this.to_account_id = to_account_id;
    }

    public String getTransferDate() {
        return transfer_date;
    }

    public void setTransferDate(String transfer_date) {
        this.transfer_date = transfer_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

