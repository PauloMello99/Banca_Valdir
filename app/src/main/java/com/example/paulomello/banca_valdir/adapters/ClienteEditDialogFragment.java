package com.example.paulomello.banca_valdir.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.paulomello.banca_valdir.models.Venda;
import com.example.paulomello.banca_valdir.R;

import java.text.NumberFormat;

public class ClienteEditDialogFragment extends DialogFragment {

    public interface NoticeDialogListener {
        void onDialogPositiveClick(Venda venda, int position);
    }

    private static final String EXTRA_TITLE = "TITLE";
    public static final String EXTRA_POSITIVE_BUTTON = "POSITIVE_BUTTON";
    private static final String EXTRA_ITEM = "SERIALIZABLE_ITEM";
    private static final String EXTRA_POSITION = "ITEM_POSITION";

    private ClienteEditDialogFragment.NoticeDialogListener listener;
    private EditText valorEditText;
    private Venda currentVenda = null;
    private NumberFormat format = NumberFormat.getCurrencyInstance();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ClienteEditDialogFragment.NoticeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement NoticeDialogListener");
        }
    }

    public static ClienteEditDialogFragment newInstance(String title, String positiveButton, Venda venda, int position) {
        ClienteEditDialogFragment dialog = new ClienteEditDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TITLE, title);
        bundle.putString(EXTRA_POSITIVE_BUTTON, positiveButton);
        bundle.putSerializable(EXTRA_ITEM, venda);
        bundle.putInt(EXTRA_POSITION, position);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static ClienteEditDialogFragment newInstance(String title, String positiveButton) {
        return newInstance(title, positiveButton, null, -1);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Context context = getActivity();
        final Bundle bundle = getArguments();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(bundle.getString(EXTRA_TITLE));
        builder.setPositiveButton(getString(R.string.edit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String valorStr = valorEditText.getText().toString();
                Double valor = Double.parseDouble(valorStr);
                Double antigo = Double.parseDouble(currentVenda.getValor());
                antigo -= valor;
                currentVenda.setValor(String.valueOf(antigo));
                if(antigo==valor)
                {
                    currentVenda.setStatus("Pago");
                }
                listener.onDialogPositiveClick(currentVenda, bundle.getInt(EXTRA_POSITION));
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_edit_purchase, null, true);
        builder.setView(view);

        setView(view, bundle);
        return builder.create();
    }

    private void setView(View view, final Bundle bundle) {
        valorEditText = view.findViewById(R.id.valor_edit);
        currentVenda = (Venda) bundle.getSerializable(EXTRA_ITEM);
        valorEditText.setText(currentVenda.getValor());
    }
}
