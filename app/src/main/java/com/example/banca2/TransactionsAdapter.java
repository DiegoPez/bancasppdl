package com.example.banca2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder> {

    private List<Movement> movements = new ArrayList<>();

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Movement movement = movements.get(position);
        holder.transactionTypeTextView.setText(movement.getTransactionType());
        holder.amountTextView.setText(String.valueOf(movement.getAmount()) + " pesos");
    }

    @Override
    public int getItemCount() {
        return movements.size();
    }

    public void setMovements(List<Movement> movements) {
        this.movements = movements;
        notifyDataSetChanged();
    }

    static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView transactionTypeTextView, amountTextView;

        TransactionViewHolder(View itemView) {
            super(itemView);
            transactionTypeTextView = itemView.findViewById(R.id.transactionTypeTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
        }
    }


}
