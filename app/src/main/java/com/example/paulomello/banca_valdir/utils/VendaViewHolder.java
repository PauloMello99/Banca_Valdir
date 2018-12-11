package com.example.paulomello.banca_valdir.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.paulomello.banca_valdir.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VendaViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.name_cliente_venda)
    TextView nameTextView;

    @BindView(R.id.valor_cliente_venda)
    TextView valorTextView;

    @BindView(R.id.venda_status)
    TextView status;

    @BindView(R.id.acerto_venda_cliente_button)
    Button acertoButton;

    @BindView(R.id.edit_venda_cliente_button)
    Button editButton;

    public TextView getStatus() {
        return status;
    }

    public TextView getNameTextView() {
        return nameTextView;
    }

    public TextView getValorTextView() {
        return valorTextView;
    }

    public Button getAcertoButton() {
        return acertoButton;
    }

    public Button getEditButton() {
        return editButton;
    }

    public VendaViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
