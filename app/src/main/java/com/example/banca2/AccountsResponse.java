package com.example.banca2;

import java.util.List;

public class AccountsResponse {
    private int code;
    private List<Account> message;

    // Constructor
    public AccountsResponse(int code, List<Account> message) {
        this.code = code;
        this.message = message;
    }

    // Getters
    public int getCode() {
        return code;
    }

    public List<Account> getMessage() {
        return message;
    }
}
