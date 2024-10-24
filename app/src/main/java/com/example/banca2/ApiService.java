package com.example.banca2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import java.util.List;


public interface ApiService {
    @POST("/users/login/")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/users/signup/")
    Call<SignupResponse> registerUser(@Body SignupRequest signupRequest);

    @GET("/accounts/all")
    Call<AccountsResponse> getAccounts(@Header("Authorization") String token);

    @POST("/accounts/create")
    Call<CreateAccountResponse> createAccount(@Header("Authorization") String token, @Body CreateAccountRequest createAccountRequest);

    @GET("/updateusers/user")
    Call<UserResponse> getUserInfo(@Header("Authorization") String token);

    // Nuevo método para realizar transferencias
    @POST("/movements/transfers")
    Call<TransferResponse> makeTransfer(@Header("Authorization") String token, @Body TransferRequest transferRequest);

}



