package com.example.paulomello.banca_valdir.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paulomello.banca_valdir.models.Compra;
import com.example.paulomello.banca_valdir.R;
import com.example.paulomello.banca_valdir.utils.CompraViewHolder;

import java.text.NumberFormat;
import java.util.List;

public class CompraAdapter extends RecyclerView.Adapter<CompraViewHolder>  {

    public interface CompraAdapterListener {
        void onPayClick(View v, int position);
        void onEditClick(View v, int position);
    }

    private List<Compra> compras;
    private Context context;
    private CompraAdapter.CompraAdapterListener listener;
    private NumberFormat format = NumberFormat.getCurrencyInstance();

    public CompraAdapter(List<Compra> compras, Context context, Fragment fragment){
        this.compras = compras;
        this.context = context;
        this.listener = (CompraAdapter.CompraAdapterListener) fragment;
    }

    @NonNull
    @Override
    public CompraViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.card_compra, viewGroup, false);
        return new CompraViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CompraViewHolder compraViewHolder, int i) {
        final Compra compra = compras.get(i);
        Double valor = Double.parseDouble(compra.getValor());
        String valorStr = format.format(valor);
        compraViewHolder.getNameTextView().setText(String.valueOf("ID: "+compra.getId_fornecedor()));
        compraViewHolder.getValorTextView().setText(valorStr);
        compraViewHolder.getStatus().setText(compra.getStatus());
        compraViewHolder.getAcertoButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPayClick(v, compraViewHolder.getAdapterPosition());
            }
        });
        compraViewHolder.getEditButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditClick(v, compraViewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return compras.size();
    }
}
