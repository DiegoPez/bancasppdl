package com.example.banca2;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.AccountViewHolder> {
    private List<Account> accountList;
    private String token; // Agregar el token como un campo

    public AccountsAdapter(List<Account> accountList, String token) {
        this.accountList = accountList;
        this.token = token; // Asignar el token recibido
    }
    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        Account account = accountList.get(position);
        holder.serialNumber.setText(String.valueOf(account.getAccountId()));
        holder.type.setText(account.getAccountType());

        // Hacer la tarjeta clickeable y llevar a la actividad Transaction
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), Transaction.class);
            intent.putExtra("accountId", account.getAccountId());
            intent.putExtra("accountType", account.getAccountType());
            intent.putExtra("balance", account.getBalance()); // Pasar el balance al Intent
            intent.putExtra("message", token); // Pasar el token al Intent

            v.getContext().startActivity(intent);
        });
    }



    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public static class AccountViewHolder extends RecyclerView.ViewHolder {
        TextView serialNumber, type;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            serialNumber = itemView.findViewById(R.id.serialNumber);
            type = itemView.findViewById(R.id.Type);
        }
    }
}

