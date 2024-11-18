package com.example.banca2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar campos
        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Botón crear cuenta
        Button btnCrearCuenta = findViewById(R.id.btnCrearCuenta);
        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, signin.class);
                startActivity(intent);
            }
        });

        // Configurar acción del botón de login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores de los campos de texto
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                // Verificar si los campos están vacíos
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, ingrese email y contraseña", Toast.LENGTH_SHORT).show();
                } else {
                    // Hacer login
                    login(email, password);
                }
            }
        });
    }

    private void login(String username, String password) {
        // Crear una instancia de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bancasppdl.click//")  // Base URL de tu API
                .addConverterFactory(GsonConverterFactory.create()) // Convertir JSON a objetos Java
                .build();

        // Crear instancia del servicio
        ApiService apiService = retrofit.create(ApiService.class);

        // Crear un objeto LoginRequest con los datos del usuario
        LoginRequest loginRequest = new LoginRequest(username, password);

        // Hacer la solicitud de login
        Call<LoginResponse> call = apiService.login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Si el login es exitoso, obtener el message del campo "message"
                    String message = response.body().getMessage();
                    Toast.makeText(MainActivity.this, "Login exitoso, message: " + message, Toast.LENGTH_SHORT).show();

                    // Pasar el message (token) a la siguiente actividad
                    Intent intent = new Intent(MainActivity.this, Page1.class);
                    intent.putExtra("message", message);  // Pasar el message a Page1
                    startActivity(intent);

                    // Finalizar la actividad de login para evitar volver a ella
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Login fallido: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Manejar errores de red o de la API
                Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
