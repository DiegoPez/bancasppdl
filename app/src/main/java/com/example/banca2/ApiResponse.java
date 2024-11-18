package com.example.banca2;

import java.util.List;

public class ApiResponse {
    private int code;
    private Object message;  // Puede ser una lista de movimientos o un mensaje de texto

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}

