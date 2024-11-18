package com.example.banca2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder> {

    private List<Movement> movements;
    private Context context;
    private int currentAccountId;

    // Constructor para inicializar con una lista de movimientos
    public TransactionsAdapter(List<Movement> movements) {
        this.movements = movements != null ? movements : new ArrayList<>();
        this.context = context;
        this.currentAccountId = currentAccountId;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movement, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Movement movement = movements.get(position);

        // Inicializar el prefijo del monto
        String amountPrefix = "";

        // Obtener los valores necesarios para las condiciones
        String transactionType = movement.getTransactionType();
        int fromAccountId = movement.getFromAccountId(); // ID de la cuenta de origen
        int requestAccountId = currentAccountId; // ID de la cuenta que hace la solicitud

        // Condiciones especiales para determinar el prefijo
        if ("deposit".equalsIgnoreCase(transactionType)) {
            amountPrefix = "+";
        } else if ("withdrawal".equalsIgnoreCase(transactionType)) {
            amountPrefix = "-";
        } else if (fromAccountId == requestAccountId) {
            amountPrefix = "-";
        } else {
            amountPrefix = "+";
        }

        // Asignar valores al TextView del monto
        holder.amountTextView.setText(amountPrefix + movement.getAmount() + " pesos");

        // Asignar valor al TextView de la fecha
        if (movement.getTransactionDate() != null) {
            holder.dateTextView.setText(movement.getTransactionDate());
        } else if (movement.getTransferDate() != null) {
            holder.dateTextView.setText(movement.getTransferDate());
        } else {
            holder.dateTextView.setText("Fecha no disponible");
        }

        // Asignar valor al TextView de la descripción
        if (movement.getDescription() != null) {
            holder.descriptionTextView.setText(movement.getDescription());
        } else {
            holder.descriptionTextView.setText("Sin descripción");
        }
    }

    @Override
    public int getItemCount() {
        return movements.size();
    }

    public void setMovements(List<Movement> movements) {
        this.movements = movements != null ? movements : new ArrayList<>();
        notifyDataSetChanged();
    }

    static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView transactionTypeTextView, amountTextView, dateTextView, descriptionTextView;

        TransactionViewHolder(View itemView) {
            super(itemView);
            transactionTypeTextView = itemView.findViewById(R.id.transactionTypeTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView); // Asegúrate de que este ID sea correcto
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView); // Asegúrate de que este ID sea correcto
        }
    }
}
