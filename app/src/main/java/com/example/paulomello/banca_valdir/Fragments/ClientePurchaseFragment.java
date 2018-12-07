package com.example.paulomello.banca_valdir.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.paulomello.banca_valdir.Adapters.VendaAdapter;
import com.example.paulomello.banca_valdir.Models.Venda;
import com.example.paulomello.banca_valdir.Providers.VendaDAO;
import com.example.paulomello.banca_valdir.R;

import java.util.ArrayList;
import java.util.List;

public class ClientePurchaseFragment extends Fragment implements VendaAdapter.VendaAdapterListener {

    public View view;
    public Context context;
    public RecyclerView recyclerView;
    public VendaAdapter adapter;
    public VendaDAO vendaDAO;
    public List<Venda> vendas = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    private void setRecyclerView() {
        recyclerView = view.findViewById(R.id.recycler_venda);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        adapter = new VendaAdapter(vendas, context,this);
        recyclerView.setAdapter(adapter);
    }

    private void setListItem() {
        vendaDAO = new VendaDAO(context);
        try {
            vendas = vendaDAO.searchAll();
        } catch (Exception e) {
            Toast.makeText(context, "Erro ao carregar vendas", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_venda,container,false);
        setListItem();
        setRecyclerView();
        return view;
    }

    @Override
    public void onPayClick(View v, int position) {

    }

    @Override
    public void onEditClick(View v, int position) {

    }
}
