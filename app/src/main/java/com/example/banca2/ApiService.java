package com.example.banca2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import java.util.List;
import retrofit2.http.Path;



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

    @POST("/movements/transactions")
    Call<TransactionResponse> makeTransaction(@Header("Authorization") String token, @Body TransactionRequest transactionRequest);

    @GET("/movements/account/{accountId}")
    Call<List<Movement>> getAccountMovements(@Header("Authorization") String token, @Path("accountId") int accountId);


}



