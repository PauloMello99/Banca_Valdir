package com.example.paulomello.banca_valdir.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paulomello.banca_valdir.models.Cliente;
import com.example.paulomello.banca_valdir.R;
import com.example.paulomello.banca_valdir.utils.ClienteViewHolder;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteViewHolder>   {

    public interface ClienteAdapterListener {
        void onNewPurchaseClick(View v, int position);
        void onViewClick(View v, int position);
        void onEditClick(View v,int position);
    }

    private List<Cliente> clientes;
    private Context context;
    private ClienteAdapterListener listener;

    public ClienteAdapter(List<Cliente> clientes, Context context, Fragment fragment) {
        this.clientes = clientes;
        this.context = context;
        this.listener = (ClienteAdapterListener)fragment;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.card_cliente, viewGroup, false);
        return new ClienteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ClienteViewHolder clienteViewHolder, int i) {
        Cliente cliente = clientes.get(i);
        clienteViewHolder.getNameTextView().setText(cliente.getName());
        clienteViewHolder.getPurchaseButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onNewPurchaseClick(v, clienteViewHolder.getAdapterPosition());
            }
        });
        clienteViewHolder.getViewButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onViewClick(v, clienteViewHolder.getAdapterPosition());
            }
        });
        clienteViewHolder.getEditButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditClick(v, clienteViewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }
}
