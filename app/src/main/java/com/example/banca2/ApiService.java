package com.example.banca2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/users/login/")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/users/signup/")
    Call<SignupResponse> registerUser(@Body SignupRequest signupRequest);
}



