package com.example.banca2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovementsAdapter extends RecyclerView.Adapter<MovementsAdapter.MovementViewHolder> {

    private List<Movement> movements;

    public MovementsAdapter(List<Movement> movements) {
        this.movements = movements;
    }

    @NonNull
    @Override
    public MovementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movement, parent, false);
        return new MovementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovementViewHolder holder, int position) {
        Movement movement = movements.get(position);

        // Asignar valores a las vistas
        holder.dateTextView.setText(movement.getTransactionDate() != null ? movement.getTransactionDate() : movement.getTransferDate());
        holder.descriptionTextView.setText(movement.getDescription() != null ? movement.getDescription() : "Transferencia");
        holder.amountTextView.setText("$" + movement.getAmount());
    }

    @Override
    public int getItemCount() {
        return movements != null ? movements.size() : 0;
    }

    public void setMovements(List<Movement> movements) {
        this.movements = movements;
        notifyDataSetChanged();
    }

    // ViewHolder
    public static class MovementViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView descriptionTextView;
        TextView amountTextView;

        public MovementViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
        }
    }
}
