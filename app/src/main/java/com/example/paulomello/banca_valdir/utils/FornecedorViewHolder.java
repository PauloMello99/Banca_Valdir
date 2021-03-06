package com.example.paulomello.banca_valdir.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.paulomello.banca_valdir.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FornecedorViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.name_fornecedor)
    TextView nameTextView;

    @BindView(R.id.new_fornecedor_purchase_button)
    Button purchaseButton;

    @BindView(R.id.view_fornecedor_button)
    Button viewButton;

    @BindView(R.id.edit_fornecedor_button)
    Button editButton;

    public TextView getNameTextView() {
        return nameTextView;
    }

    public Button getPurchaseButton() {
        return purchaseButton;
    }

    public Button getViewButton() {
        return viewButton;
    }

    public Button getEditButton() {
        return editButton;
    }

    public FornecedorViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
