
package com.example.banca2;

import java.util.List;

public class MovementResponse {
    private int code;
    private List<Movement> message;

    // Constructor
    public MovementResponse(int code, List<Movement> message) {
        this.code = code;
        this.message = message;
    }

    // Getters
    public int getCode() {
        return code;
    }

    public List<Movement> getMessage() {
        return message;
    }
}
