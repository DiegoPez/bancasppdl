package com.example.banca2;

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

    public AccountsAdapter(List<Account> accountList) {
        this.accountList = accountList;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el layout de la tarjeta
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        Account account = accountList.get(position);
        holder.serialNumber.setText(String.valueOf(account.getAccountId())); // Asigna el ID de la cuenta
        holder.type.setText(account.getAccountType()); // Asigna el tipo de cuenta

        Log.d("AccountsAdapter", "ID: " + account.getAccountId() + ", Type: " + account.getAccountType());
    }

    @Override
    public int getItemCount() {
        return accountList.size(); // Retorna la cantidad de cuentas
    }

    public static class AccountViewHolder extends RecyclerView.ViewHolder {
        TextView serialNumber, type; // Variables para el ID y el tipo de cuenta

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            serialNumber = itemView.findViewById(R.id.serialNumber); // Encuentra el TextView para el ID
            type = itemView.findViewById(R.id.Type); // Encuentra el TextView para el tipo de cuenta
        }
    }
}
