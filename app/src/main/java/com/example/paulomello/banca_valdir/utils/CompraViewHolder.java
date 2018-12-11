package com.example.paulomello.banca_valdir.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.paulomello.banca_valdir.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompraViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.name_fornecedor_compra)
    TextView nameTextView;

    @BindView(R.id.valor_fornecedor_compra)
    TextView valorTextView;

    @BindView(R.id.compra_status)
    TextView status;

    @BindView(R.id.acerto_compra_fornecedor_button)
    Button acertoButton;

    @BindView(R.id.edit_compra_fornecedor_button)
    Button editButton;

    public TextView getNameTextView() {
        return nameTextView;
    }

    public TextView getValorTextView() {
        return valorTextView;
    }

    public TextView getStatus() {
        return status;
    }

    public Button getAcertoButton() {
        return acertoButton;
    }

    public Button getEditButton() {
        return editButton;
    }

    public CompraViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
