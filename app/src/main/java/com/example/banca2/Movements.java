package com.example.banca2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Movements extends AppCompatActivity {

    private ApiService apiService;
    private String token; // Asegúrate de tener el token de autenticación
    private int fromAccountId; // La cuenta desde donde se hace la transferencia

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movements);

        // Inicializa el ApiService
        apiService = ApiClient.getClient().create(ApiService.class);

        // Obtener los extras del Intent
        fromAccountId = getIntent().getIntExtra("accountId", -1);
        token = getIntent().getStringExtra("message");

        // Referencias a los elementos de la interfaz
        EditText toAccountIdEditText = findViewById(R.id.editTextNumber);
        EditText amountEditText = findViewById(R.id.editTextNumber2);
        EditText descriptionEditText = findViewById(R.id.editTextText2);
        Button transferButton = findViewById(R.id.button3);

        // Configura el botón de transferencia
        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int toAccountId = Integer.parseInt(toAccountIdEditText.getText().toString());
                double amount = Double.parseDouble(amountEditText.getText().toString());
                String description = descriptionEditText.getText().toString();

                makeTransfer(toAccountId, amount, description);
            }
        });
    }

    private void makeTransfer(int toAccountId, double amount, String description) {
        TransferRequest transferRequest = new TransferRequest(fromAccountId, toAccountId, amount, description);

        Call<TransferResponse> call = apiService.makeTransfer("Bearer " + token, transferRequest);
        call.enqueue(new Callback<TransferResponse>() {
            @Override
            public void onResponse(Call<TransferResponse> call, Response<TransferResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(Movements.this, "Transferencia exitosa: " + response.body().getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Movements.this, "Error en la transferencia", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TransferResponse> call, Throwable t) {
                Toast.makeText(Movements.this, "Error de red: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
