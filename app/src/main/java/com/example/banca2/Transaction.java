package com.example.banca2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Transaction extends AppCompatActivity {

    private RecyclerView transactionsRecyclerView;
    private TransactionsAdapter transactionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        // Obtener los extras del Intent
        int accountId = getIntent().getIntExtra("accountId", -1);
        String accountType = getIntent().getStringExtra("accountType");
        double balance = getIntent().getDoubleExtra("balance", 0);
        String token = getIntent().getStringExtra("message");

        if (token == null || token.isEmpty()) {
            Toast.makeText(this, "Error: Token de autenticación no encontrado", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Referencias a los elementos de la tarjeta
        TextView serialNumber = findViewById(R.id.serialNumber);
        TextView type = findViewById(R.id.Type);
        TextView balanceTextView = findViewById(R.id.textView6);
        ImageButton imageButton2 = findViewById(R.id.imageButton2);
        ImageButton imageButton3 = findViewById(R.id.imageButton3);

        serialNumber.setText(String.valueOf(accountId));
        type.setText(accountType);
        balanceTextView.setText(balance + " pesos");

        // Botones para navegación
        imageButton2.setOnClickListener(v -> {
            Intent intent = new Intent(Transaction.this, Movements.class);
            intent.putExtra("accountId", accountId);
            intent.putExtra("message", token);
            startActivity(intent);
        });

        imageButton3.setOnClickListener(v -> {
            Intent intent = new Intent(Transaction.this, RTransaction.class);
            intent.putExtra("accountId", accountId);
            intent.putExtra("message", token);
            startActivity(intent);
        });

        // Configurar RecyclerView
        setupRecyclerView(accountId, token);
    }

    private void setupRecyclerView(int accountId, String token) {
        transactionsRecyclerView = findViewById(R.id.transactionsRecyclerView);
        transactionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        transactionsAdapter = new TransactionsAdapter(new ArrayList<>());
        transactionsRecyclerView.setAdapter(transactionsAdapter);

        loadTransactions(accountId, token);
    }


    private void loadTransactions(int accountId, String token) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<MovementResponse> call = apiService.getAccountMovements("Bearer " + token, accountId);

        call.enqueue(new Callback<MovementResponse>() {
            @Override
            public void onResponse(Call<MovementResponse> call, Response<MovementResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movement> movements = response.body().getMessage();
                    transactionsAdapter.setMovements(movements); // Actualiza la lista en el adaptador
                } else {
                    Toast.makeText(Transaction.this, "Error al cargar movimientos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovementResponse> call, Throwable t) {
                Toast.makeText(Transaction.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

