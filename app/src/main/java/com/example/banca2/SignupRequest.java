package com.example.banca2;

public class SignupRequest {
    private String username;
    private String password;
    private String email;
    private String first_name;
    private String last_name;

    public SignupRequest(String username, String password, String email, String first_name, String last_name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
    }

}
