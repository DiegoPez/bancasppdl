package com.example.banca2;

import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        accountsRecyclerView = findViewById(R.id.accountsRecyclerView);
        accountsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener el message (token) desde el Intent
        String message = getIntent().getStringExtra("message");

        fetchAccounts(message);  // Pasar el message (token) al método fetchAccounts
    }

    private void fetchAccounts(String message) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Log.d("DEBUG", "Token recibido: " + message);

        Call<AccountsResponse> call = apiService.getAccounts("Bearer " + message);

        Log.d("DEBUG", "URL de la solicitud: " + call.request().url());
        Log.d("DEBUG", "Headers: " + call.request().headers().toString());

        call.enqueue(new Callback<AccountsResponse>() {
            @Override
            public void onResponse(Call<AccountsResponse> call, Response<AccountsResponse> response) {
                Log.d("DEBUG", "Cuentas recibidas: " + accountList.size());
                if (response.isSuccessful() && response.body() != null) {
                    accountList = response.body().getMessage(); // Obtener la lista de cuentas desde el mensaje
                    accountsAdapter = new AccountsAdapter(accountList);
                    accountsRecyclerView.setAdapter(accountsAdapter);
                } else {
                    Toast.makeText(Page1.this, "Error al cargar cuentas", Toast.LENGTH_SHORT).show();
                    Log.e("ERROR", "Código de respuesta: " + response.code() + ", Mensaje: " + response.message());

                    // Imprimir cuerpo de error si lo hay
                    try {
                        Log.e("ERROR_BODY", response.errorBody().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AccountsResponse> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
                Toast.makeText(Page1.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }

        });
        // Botón Agregar
        buttonAgregar = findViewById(R.id.button);

        // Configurar el evento onClick para el botón Agregar
        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent para ir a la página de registro de cuenta
                Intent intent = new Intent(Page1.this, registerAccount.class);

                // Pasar el token como extra

                intent.putExtra("message", message); // Pasar el token (message) a registerAccount
                startActivity(intent);
            }
        });
    }


}