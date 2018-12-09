package com.example.paulomello.banca_valdir.Fragments;

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

import com.example.paulomello.banca_valdir.Adapters.ClienteEditDialogFragment;
import com.example.paulomello.banca_valdir.Adapters.ClientePayDialogFragment;
import com.example.paulomello.banca_valdir.Adapters.VendaAdapter;
import com.example.paulomello.banca_valdir.Models.Venda;
import com.example.paulomello.banca_valdir.Providers.VendaDAO;
import com.example.paulomello.banca_valdir.R;

import java.util.ArrayList;
import java.util.List;

public class ClientePurchaseFragment extends Fragment implements VendaAdapter.VendaAdapterListener {

    private View view;
    private static Context context;
    private static RecyclerView recyclerView;
    private static VendaAdapter adapter;
    private static VendaDAO vendaDAO;
    public  static List<Venda> vendas = new ArrayList<>();

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
            AlertDialog alertDialog = new AlertDialog.Builder(context).setMessage("Erro: \n"+e).show();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_venda,container,false);
        setListItem();
        setRecyclerView();
        return view;
    }

    private void showPayDialogFragment(int position){
        FragmentManager fragmentManager = getFragmentManager();
        ClientePayDialogFragment dialog;
        dialog = ClientePayDialogFragment.newInstance(getString(R.string.acerto),getString(R.string.add),vendas.get(position),position);
        dialog.setCancelable(false);
        dialog.show(fragmentManager, getString(R.string.acerto));
    }

    private void showEditDialogFragment(int position){
        FragmentManager fragmentManager = getFragmentManager();
        ClienteEditDialogFragment dialog;
        dialog = ClienteEditDialogFragment.newInstance(getString(R.string.edit),getString(R.string.update),vendas.get(position),position);
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

    public static void updateVendas(Venda venda, int position)
    {
        try {
            if(venda.getId()==0)
            {
                vendaDAO.create(venda);
                vendas.add(venda);
                recyclerView.scrollToPosition(adapter.getItemCount());
                Toast.makeText(context,"Adicionado com sucesso",Toast.LENGTH_LONG).show();
            }
            else
            {
                vendaDAO.update(venda);
                vendas.set(position,venda);
                Toast.makeText(context,"Atualizado com sucesso",Toast.LENGTH_LONG).show();
            }
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            AlertDialog builder = new AlertDialog.Builder(context).setMessage(""+e).show();
        }
    }
}
