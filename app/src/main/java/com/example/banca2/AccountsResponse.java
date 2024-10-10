package com.example.banca2;

import java.util.List;

public class AccountsResponse {
    private int code;
    private List<Account> message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Account> getMessage() {
        return message;
    }

    public void setMessage(List<Account> message) {
        this.message = message;
    }
}
