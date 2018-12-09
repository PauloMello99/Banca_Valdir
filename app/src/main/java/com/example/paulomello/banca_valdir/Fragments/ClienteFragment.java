package com.example.paulomello.banca_valdir.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.example.paulomello.banca_valdir.Adapters.ClienteAdapter;
import com.example.paulomello.banca_valdir.Adapters.ClienteDialogFragment;
import com.example.paulomello.banca_valdir.Adapters.ClientePurchaseDialogFragment;
import com.example.paulomello.banca_valdir.Models.Cliente;
import com.example.paulomello.banca_valdir.Models.Venda;
import com.example.paulomello.banca_valdir.Providers.ClienteDAO;
import com.example.paulomello.banca_valdir.Providers.VendaDAO;
import com.example.paulomello.banca_valdir.R;

import java.util.ArrayList;
import java.util.List;

public class ClienteFragment extends Fragment implements ClienteAdapter.ClienteAdapterListener{


    private View view;
    private static Context context;
    public  static List<Cliente> clientes = new ArrayList<>();
    private static ClienteDAO clienteDAO;
    private static ClienteAdapter adapter;
    private static RecyclerView recyclerView;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    private void setRecyclerView() {
        recyclerView = view.findViewById(R.id.recycler_cliente);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        adapter = new ClienteAdapter(clientes, context,this);
        recyclerView.setAdapter(adapter);
    }

    private void setListItem() {
        clienteDAO = new ClienteDAO(context);
        try {
            clientes = clienteDAO.searchAll();
        } catch (Exception e) {
            Toast.makeText(context, "Erro ao carregar clientes", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cliente,container,false);
        setListItem();
        setRecyclerView();
        return view;
    }

    private void showClienteDialogFragment(String tag, int position) {
        FragmentManager fragmentManager = getFragmentManager();
        ClienteDialogFragment dialog;
        if (tag.equals(ClienteDialogFragment.CREATE_TAG)) {
            dialog = ClienteDialogFragment.newInstance(getString(R.string.new_client), getString(R.string.add));
            dialog.setCancelable(false);
        } else {
            dialog = ClienteDialogFragment.newInstance(getString(R.string.edit_client), getString(R.string.update), clientes.get(position), position);
        }
        dialog.show(fragmentManager, tag);
    }

    private void showClientePurchaseDialogFragment(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        ClientePurchaseDialogFragment dialog;
        dialog = ClientePurchaseDialogFragment.newInstance(getString(R.string.new_venda),getString(R.string.add),clientes.get(position),position);
        dialog.setCancelable(false);
        dialog.show(fragmentManager, getString(R.string.new_venda));
    }

    private void showConfirmDeleteDialog(final int position) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setTitle(R.string.deleting);
        builder.setMessage(R.string.delete_cliente);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int arg1) {
                try
                {
                    clienteDAO.delete(clientes.get(position));
                    clientes.remove(position);
                    adapter.notifyItemRemoved(position);
                    adapter.notifyDataSetChanged();
                }
                catch (Exception e)
                {
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
                    builder.setTitle("Erro");
                    builder.setMessage(""+e);
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.create();
        builder.show();
    }


    @Override
    public void onNewPurchaseClick(View v, int position) {
        showClientePurchaseDialogFragment(position);
    }

    @Override
    public void onViewClick(View v, int position) {
        showConfirmDeleteDialog(position);
    }

    @Override
    public void onEditClick(View v, int position) {
        showClienteDialogFragment(ClienteDialogFragment.EDIT_TAG, position);
    }

    public static void updateClientes(Cliente cliente, int position)
    {
        try {
            if(cliente.getId()==0)
            {
                clienteDAO.create(cliente);
                clientes.add(cliente);
                recyclerView.scrollToPosition(adapter.getItemCount());
                Toast.makeText(context,"Adicionado com sucesso",Toast.LENGTH_LONG).show();
            }
            else
            {
                clienteDAO.update(cliente);
                clientes.set(position,cliente);
                Toast.makeText(context,"Atualizado com sucesso",Toast.LENGTH_LONG).show();
            }
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            AlertDialog builder = new AlertDialog.Builder(context).setMessage(""+e).show();
        }
    }
}