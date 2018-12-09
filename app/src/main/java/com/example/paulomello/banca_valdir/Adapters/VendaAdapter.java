package com.example.paulomello.banca_valdir.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paulomello.banca_valdir.Models.Venda;
import com.example.paulomello.banca_valdir.R;
import com.example.paulomello.banca_valdir.Utils.VendaViewHolder;

import java.text.NumberFormat;
import java.util.List;

public class VendaAdapter extends RecyclerView.Adapter<VendaViewHolder> {

    public interface VendaAdapterListener {
        void onPayClick(View v, int position);
        void onEditClick(View v, int position);
    }

    private List<Venda> vendas;
    private Context context;
    private VendaAdapterListener listener;
    private NumberFormat format = NumberFormat.getCurrencyInstance();

    public VendaAdapter(List<Venda> vendas, Context context, Fragment fragment){
        this.vendas = vendas;
        this.context = context;
        this.listener = (VendaAdapterListener) fragment;
    }

    @NonNull
    @Override
    public VendaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.card_venda, viewGroup, false);
        return new VendaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final VendaViewHolder vendaViewHolder, int i) {
        final Venda venda = vendas.get(i);
        Double valor = Double.parseDouble(venda.getValor());
        String valorStr = format.format(valor);
        vendaViewHolder.getNameTextView().setText(String.valueOf("ID: "+venda.getId_cliente()));
        vendaViewHolder.getValorTextView().setText(valorStr);
        vendaViewHolder.getStatus().setText(venda.getStatus());
        vendaViewHolder.getAcertoButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPayClick(v, vendaViewHolder.getAdapterPosition());
            }
        });
        vendaViewHolder.getEditButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditClick(v, vendaViewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return vendas.size();
    }
}