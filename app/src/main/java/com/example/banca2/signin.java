package com.example.banca2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class signin extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText, emailEditText, firstNameEditText, lastNameEditText;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicializar vistas
        usernameEditText = findViewById(R.id.editTextText);
        passwordEditText = findViewById(R.id.editTextTextPassword2);
        emailEditText = findViewById(R.id.editTextTextEmailAddress2);
        firstNameEditText = findViewById(R.id.editTextText2);
        lastNameEditText = findViewById(R.id.editTextText3);
        signupButton = findViewById(R.id.button2);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String first_name = firstNameEditText.getText().toString();
                String last_name = lastNameEditText.getText().toString();

                registerUser(username, password, email, first_name, last_name);
            }
        });
    }

    private void registerUser(String username, String password, String email, String first_name, String last_name) {
        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://18.190.3.213:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        SignupRequest signupRequest = new SignupRequest(username, password, email, first_name, last_name);

        // Hacer la llamada a la API
        Call<SignupResponse> call = apiService.registerUser(signupRequest);
        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (response.isSuccessful()) {
                    SignupResponse signupResponse = response.body();
                    Toast.makeText(signin.this, "Registro exitoso: " + signupResponse.getMessage(), Toast.LENGTH_LONG).show();

                    // Redirigir al login (MainActivity)
                    Intent intent = new Intent(signin.this, MainActivity.class);
                    startActivity(intent);
                    finish();  // Finaliza la actividad actual para que el usuario no pueda volver al registro con el botón de atrás
                } else {
                    // Obtener más detalles del error
                    try {
                        String errorBody = response.errorBody().string();
                        Toast.makeText(signin.this, "Error en el registro: " + errorBody, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(signin.this, "Error en el registro y al procesar la respuesta", Toast.LENGTH_LONG).show();
                    }
                }
            }


            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Log.e("Signup Error", t.getMessage());
                Toast.makeText(signin.this, "Error de conexión", Toast.LENGTH_LONG).show();
            }
        });
    }
}
