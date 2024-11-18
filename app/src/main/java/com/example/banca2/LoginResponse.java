package com.example.banca2;

public class LoginResponse {
    private int code;
    private String message; // Este campo contiene el token

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
