package com.example.banca2;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private User message;

    // Getters y setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public User getMessage() {
        return message;
    }

    public void setMessage(User message) {
        this.message = message;
    }
}
