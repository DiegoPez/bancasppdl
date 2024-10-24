package com.example.banca2;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Page1 extends AppCompatActivity {

    private RecyclerView accountsRecyclerView;
    private AccountsAdapter accountsAdapter;
    private List<Account> accountList = new ArrayList<>();
    private Button buttonAgregar;
    private TextView textViewName, textViewInitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        accountsRecyclerView = findViewById(R.id.accountsRecyclerView);
        accountsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        textViewName = findViewById(R.id.textView2);
        textViewInitial = findViewById(R.id.textView);

        // Obtener el message (token) desde el Intent
        String message = getIntent().getStringExtra("message");

        fetchUserInfo(message);  // Obtener la información del usuario
        fetchAccounts(message);  // Obtener las cuentas del usuario

        // Botón Agregar
        buttonAgregar = findViewById(R.id.button);

        // Configurar el evento onClick para el botón Agregar
        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Page1.this, registerAccount.class);
                intent.putExtra("message", message); // Pasar el token (message) a registerAccount
                startActivity(intent);
            }
        });
    }

    private void fetchUserInfo(String message) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<UserResponse> call = apiService.getUserInfo("Bearer " + message);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body().getMessage();
                    String fullName = user.getFirstName() + " " + user.getLastName();
                    String initial = user.getUsername().substring(0, 1).toUpperCase();
                    textViewName.setText(user.getFirstName());
                    textViewInitial.setText(initial);

                } else {
                    Toast.makeText(Page1.this, "Error al obtener información del usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(Page1.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchAccounts(String message) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<AccountsResponse> call = apiService.getAccounts("Bearer " + message);

        call.enqueue(new Callback<AccountsResponse>() {
            @Override
            public void onResponse(Call<AccountsResponse> call, Response<AccountsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    accountList = response.body().getMessage(); // Obtener la lista de cuentas desde el mensaje
                    accountsAdapter = new AccountsAdapter(accountList, message); // Pasar el token (message) al adaptador
                    accountsRecyclerView.setAdapter(accountsAdapter);
                } else {
                    Toast.makeText(Page1.this, "Error al cargar cuentas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AccountsResponse> call, Throwable t) {
                Toast.makeText(Page1.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
