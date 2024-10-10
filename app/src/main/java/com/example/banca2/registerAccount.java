package com.example.banca2;

import android.content.Intent;
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

public class registerAccount extends AppCompatActivity {

    private EditText accountTypeEditText;
    private Button createButton;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        // Obtener el token desde el Intent
        token = getIntent().getStringExtra("message");

        // Verificar que el token no sea nulo
        if (token == null || token.isEmpty()) {
            Toast.makeText(this, "Error: no se recibió token", Toast.LENGTH_LONG).show();
            finish(); // Cierra la actividad si no hay token
        }

        accountTypeEditText = findViewById(R.id.editTextText);
        createButton = findViewById(R.id.button2);

        // Manejar el clic en el botón "Create"
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountType = accountTypeEditText.getText().toString().trim();

                if (accountType.isEmpty()) {
                    Toast.makeText(registerAccount.this, "Por favor, ingresa un tipo de cuenta", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Crear la cuenta llamando a la API
                createAccount(accountType);
            }
        });
    }

    private void createAccount(String accountType) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        // Crear el objeto de la solicitud
        CreateAccountRequest request = new CreateAccountRequest(accountType, 1);

        Log.d("DEBUG", "Solicitud: account_type = " + accountType + ", balance = 0");
        Call<CreateAccountResponse> call = apiService.createAccount("Bearer " + token, request);
        Log.d("DEBUG", "Token enviado: Bearer " + token);

        call.enqueue(new Callback<CreateAccountResponse>() {
            @Override
            public void onResponse(Call<CreateAccountResponse> call, Response<CreateAccountResponse> response) {
                Log.d("3333DEBUG", "Token enviado: Bearer " + token);

                if (response.isSuccessful()) {
                    Toast.makeText(registerAccount.this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(registerAccount.this, Page1.class);
                    intent.putExtra("message", token);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(registerAccount.this, "Error al crear la cuenta", Toast.LENGTH_SHORT).show();
                    Log.e("ERROR", "Codigo de respuesta: " + response.code());

                    // Imprimir el cuerpo de error
                    try {
                        Log.e("ERROR_BODY", response.errorBody().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


            @Override
            public void onFailure(Call<CreateAccountResponse> call, Throwable t) {
                Toast.makeText(registerAccount.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                Log.e("ERROR", t.getMessage());
            }
        });
    }
}
