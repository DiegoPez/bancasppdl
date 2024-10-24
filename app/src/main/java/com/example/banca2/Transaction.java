package com.example.banca2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Transaction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        // Obtener los extras del Intent
        int accountId = getIntent().getIntExtra("accountId", -1);
        String accountType = getIntent().getStringExtra("accountType");
        double balance = getIntent().getDoubleExtra("balance", 0);
        String token = getIntent().getStringExtra("message"); // Obtener el token desde el Intent

        // Verificar que el token no sea nulo o vacío
        if (token == null || token.isEmpty()) {
            Toast.makeText(this, "Error: Token de autenticación no encontrado", Toast.LENGTH_SHORT).show();
            finish(); // Cerrar la actividad si no hay token disponible
            return;
        }

        // Referencias a los elementos de la tarjeta en activity_transaction.xml
        TextView serialNumber = findViewById(R.id.serialNumber);
        TextView type = findViewById(R.id.Type);
        TextView balanceTextView = findViewById(R.id.textView6);
        ImageView cardImage = findViewById(R.id.cardImage);
        ImageButton imageButton2 = findViewById(R.id.imageButton2);
        ImageButton imageButton3 = findViewById(R.id.imageButton3);

        // Actualizar los textos con la información de la cuenta
        serialNumber.setText(String.valueOf(accountId));
        type.setText(accountType);
        balanceTextView.setText(balance + " pesos");

        // Configurar el OnClickListener para el botón
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear el Intent para ir a la actividad Movements
                Intent intent = new Intent(Transaction.this, Movements.class);
                // Pasar el accountId y el token al Intent
                intent.putExtra("accountId", accountId);
                intent.putExtra("message", token); // Pasar el token para autenticación en Movements
                startActivity(intent);
            }
        });
        // Configurar el OnClickListener para el botón
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear el Intent para ir a la actividad Movements
                Intent intent = new Intent(Transaction.this, RTransaction.class);
                // Pasar el accountId y el token al Intent
                intent.putExtra("accountId", accountId);
                intent.putExtra("message", token); // Pasar el token para autenticación en Movements
                startActivity(intent);
            }
        });
    }
}
