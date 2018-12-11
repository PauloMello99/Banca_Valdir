package com.example.paulomello.banca_valdir.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.paulomello.banca_valdir.adapters.CompraAdapter;
import com.example.paulomello.banca_valdir.adapters.FornecedorEditDialogFragment;
import com.example.paulomello.banca_valdir.adapters.FornecedorPayDialogFragment;
import com.example.paulomello.banca_valdir.models.Compra;
import com.example.paulomello.banca_valdir.providers.CompraDAO;
import com.example.paulomello.banca_valdir.R;

import java.util.ArrayList;
import java.util.List;

public class FornecedorPurchaseFragment extends Fragment implements CompraAdapter.CompraAdapterListener {

    private View view;
    private static Context context;
    private static RecyclerView recyclerView;
    private static CompraAdapter adapter;
    private static CompraDAO compraDAO;
    public  static List<Compra> compras = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_compra,container,false);
        setListItem();
        setRecyclerView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    private void setRecyclerView() {
        recyclerView = view.findViewById(R.id.recycler_compra);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        adapter = new CompraAdapter(compras, context,this);
        recyclerView.setAdapter(adapter);
    }

    private void setListItem() {
        compraDAO = new CompraDAO(context);
        try {
            compras = compraDAO.searchAll();
        } catch (Exception e) {
            AlertDialog alertDialog = new AlertDialog.Builder(context).setMessage("Erro: \n"+e).show();
        }
    }

    private void showPayDialogFragment(int position){
        FragmentManager fragmentManager = getFragmentManager();
        FornecedorPayDialogFragment dialog;
        dialog = FornecedorPayDialogFragment.newInstance(getString(R.string.acerto),getString(R.string.add),compras.get(position),position);
        dialog.setCancelable(false);
        dialog.show(fragmentManager, getString(R.string.acerto));
    }

    private void showEditDialogFragment(int position){
        FragmentManager fragmentManager = getFragmentManager();
        FornecedorEditDialogFragment dialog;
        dialog = FornecedorEditDialogFragment.newInstance(getString(R.string.edit),getString(R.string.update),compras.get(position),position);
        dialog.setCancelable(false);
        dialog.show(fragmentManager,getString(R.string.edit));
    }

    @Override
    public void onPayClick(View v, int position) {
showPayDialogFragment(position);
    }

    @Override
    public void onEditClick(View v, int position) {
        showEditDialogFragment(position);
    }

    public static void updateCompras(Compra compra, int position)
    {
        try {
            if(compra.getId()==0)
            {
                compraDAO.create(compra);
                compras.add(compra);
                recyclerView.scrollToPosition(adapter.getItemCount());
                Toast.makeText(context,"Adicionado com sucesso",Toast.LENGTH_LONG).show();
            }
            else
            {
                compraDAO.update(compra);
                compras.set(position,compra);
                Toast.makeText(context,"Atualizado com sucesso",Toast.LENGTH_LONG).show();
            }
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            AlertDialog builder = new AlertDialog.Builder(context).setMessage(""+e).show();
        }
    }
}
