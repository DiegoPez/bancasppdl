package com.example.banca2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RTransaction extends AppCompatActivity {

    private static final String TAG = "RTransaction";
    private ApiService apiService;
    private String token;
    private int accountId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rtransaction);

        // Inicializa el ApiService
        apiService = ApiClient.getClient().create(ApiService.class);

        // Obtener los extras del Intent
        accountId = getIntent().getIntExtra("accountId", -1);
        token = getIntent().getStringExtra("message");

        // Agregar logs para verificar los valores
        Log.d(TAG, "onCreate: accountId = " + accountId);
        Log.d(TAG, "onCreate: token = " + token);

        // Validar si los valores son correctos
        if (accountId == -1 || token == null || token.isEmpty()) {
            Toast.makeText(this, "Error: Datos de cuenta o token no válidos", Toast.LENGTH_LONG).show();
            Log.e(TAG, "Error: Datos de cuenta o token no válidos");
            return;
        }

        // Referencias a los elementos de la interfaz
        EditText transactionTypeEditText = findViewById(R.id.editTextNumber);
        EditText amountEditText = findViewById(R.id.editTextNumber2);
        EditText descriptionEditText = findViewById(R.id.editTextText2);
        Button transactionButton = findViewById(R.id.button3);

        // Configura el botón de transacción
        transactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String transactionType = transactionTypeEditText.getText().toString();
                    double amount = Double.parseDouble(amountEditText.getText().toString());
                    String description = descriptionEditText.getText().toString();

                    Log.d(TAG, "onClick: transactionType = " + transactionType);
                    Log.d(TAG, "onClick: amount = " + amount);
                    Log.d(TAG, "onClick: description = " + description);

                    makeTransaction(transactionType, amount, description);

                } catch (NumberFormatException e) {
                    Toast.makeText(RTransaction.this, "Por favor, ingrese un monto válido", Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Error al convertir el monto: " + e.getMessage());
                }
            }
        });
    }

    private void makeTransaction(String transactionType, double amount, String description) {
        TransactionRequest transactionRequest = new TransactionRequest(accountId, transactionType, amount, description);

        Log.d(TAG, "makeTransaction: Iniciando transacción con accountId = " + accountId + ", tipo = " + transactionType + ", monto = " + amount);

        Call<TransactionResponse> call = apiService.makeTransaction("Bearer " + token, transactionRequest);
        call.enqueue(new Callback<TransactionResponse>() {
            @Override
            public void onResponse(Call<TransactionResponse> call, Response<TransactionResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "onResponse: Transacción exitosa");
                    Toast.makeText(RTransaction.this, "Transacción exitosa: " + response.body().getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Log.e(TAG, "onResponse: Error en la transacción, código de respuesta = " + response.code());
                    Toast.makeText(RTransaction.this, "Error en la transacción", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TransactionResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: Error de red - " + t.getMessage());
                Toast.makeText(RTransaction.this, "Error de red: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
