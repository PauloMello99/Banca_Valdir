package com.example.paulomello.banca_valdir.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulomello.banca_valdir.Adapters.ClientePurchaseDialogFragment;
import com.example.paulomello.banca_valdir.Adapters.FornecedorAdapter;
import com.example.paulomello.banca_valdir.Adapters.FornecedorDialogFragment;
import com.example.paulomello.banca_valdir.Adapters.FornecedorPurchaseDialogFragment;
import com.example.paulomello.banca_valdir.Models.Fornecedor;
import com.example.paulomello.banca_valdir.Providers.FornecedorDAO;
import com.example.paulomello.banca_valdir.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class FornecedorFragment extends Fragment implements FornecedorAdapter.FornecedorAdapterListener {

    private View view;
    private static Context context;
    public static List<Fornecedor> fornecedores = new ArrayList<>();
    private static FornecedorDAO fornecedorDAO;
    private static FornecedorAdapter adapter;
    private static RecyclerView recyclerView;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    private void setRecyclerView() {
        try
        {
            recyclerView = view.findViewById(R.id.recycler_fornecedor);

            RecyclerView.LayoutManager layout = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layout);
            recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
            adapter = new FornecedorAdapter(fornecedores, context, this);
            recyclerView.setAdapter(adapter);
        }
        catch (Exception e)
        {
            AlertDialog builder = new AlertDialog.Builder(context).setMessage(""+e).show();
        }
    }

    private void setListItem() {
        fornecedorDAO = new FornecedorDAO(context);
        try {
            fornecedores = fornecedorDAO.searchAll();
        } catch (Exception e) {
            Toast.makeText(context, "Erro ao carregar fornecedores", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fornecedor,container,false);
        setListItem();
        setRecyclerView();
        return view;
    }

    private void showFornecedorDialogFragment(String tag, int position) {
        FragmentManager fragmentManager = getFragmentManager();
        FornecedorDialogFragment dialog;
        if (tag.equals(FornecedorDialogFragment.CREATE_TAG)) {
            dialog = FornecedorDialogFragment.newInstance(getString(R.string.new_fornecedor), getString(R.string.add));
            dialog.setCancelable(false);
        } else {
            dialog = FornecedorDialogFragment.newInstance(getString(R.string.edit_fornecedor), getString(R.string.update), fornecedores.get(position), position);
        }
        dialog.show(fragmentManager, tag);
    }

    private void showConfirmDeleteDialog(final int position) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setTitle(R.string.deleting);
        builder.setMessage(R.string.delete_fornecedor);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int arg1) {
                try
                {
                    fornecedorDAO.delete(fornecedores.get(position));
                    fornecedores.remove(position);
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

    private void showFornecedorPurchaseDialogFragment(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        FornecedorPurchaseDialogFragment dialog;
        dialog = FornecedorPurchaseDialogFragment.newInstance(getString(R.string.new_compra),getString(R.string.add),fornecedores.get(position),position);
        dialog.setCancelable(false);
        dialog.show(fragmentManager, getString(R.string.new_compra));
    }

    @Override
    public void onNewPurchaseClick(View v, int position) {
        showFornecedorPurchaseDialogFragment(position);
    }

    @Override
    public void onViewClick(View v, int position) {
        showConfirmDeleteDialog(position);
    }

    @Override
    public void onEditClick(View v, int position) {
        showFornecedorDialogFragment(FornecedorDialogFragment.EDIT_TAG, position);
    }

    public static void updateFornecedores(Fornecedor fornecedor, int position)
    {
        try {
            if(fornecedor.getId()==0)
            {
                fornecedorDAO.create(fornecedor);
                fornecedores.add(fornecedor);

                recyclerView.scrollToPosition(adapter.getItemCount());
                Toast.makeText(context,"Adicionado com sucesso",Toast.LENGTH_LONG).show();
            }
            else
            {
                fornecedorDAO.update(fornecedor);
                fornecedores.set(position,fornecedor);
                Toast.makeText(context,"Atualizado com sucesso",Toast.LENGTH_LONG).show();
            }
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            AlertDialog builder = new AlertDialog.Builder(context).setMessage(""+e).show();
        }
    }
}