package com.example.paulomello.banca_valdir.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paulomello.banca_valdir.Models.Fornecedor;
import com.example.paulomello.banca_valdir.R;
import com.example.paulomello.banca_valdir.Utils.FornecedorViewHolder;

import java.util.List;

public class FornecedorAdapter extends RecyclerView.Adapter<FornecedorViewHolder> {

    public interface FornecedorAdapterListener {
        void onNewPurchaseClick(View v, int position);
        void onViewClick(View v, int position);
        void onEditClick(View v,int position);
    }

    private List<Fornecedor> fornecedores;
    private Context context;
    private FornecedorAdapter.FornecedorAdapterListener listener;

    public FornecedorAdapter(List<Fornecedor> fornecedores, Context context, Fragment fragment){
        this.fornecedores = fornecedores;
        this.context = context;
        this.listener = (FornecedorAdapterListener) fragment;
    }

    @NonNull
    @Override
    public FornecedorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.card_fornecedor, viewGroup, false);
        return new FornecedorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FornecedorViewHolder fornecedorViewHolder, int i) {
        final Fornecedor fornecedor = fornecedores.get(i);
        fornecedorViewHolder.getNameTextView().setText(fornecedor.getName());
        fornecedorViewHolder.getPurchaseButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onNewPurchaseClick(v, fornecedorViewHolder.getAdapterPosition());
            }
        });
        fornecedorViewHolder.getViewButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onViewClick(v, fornecedorViewHolder.getAdapterPosition());
            }
        });
        fornecedorViewHolder.getEditButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditClick(v, fornecedorViewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return fornecedores.size();
    }
}
