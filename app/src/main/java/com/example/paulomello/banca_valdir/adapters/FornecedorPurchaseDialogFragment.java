package com.example.paulomello.banca_valdir.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.paulomello.banca_valdir.models.Compra;
import com.example.paulomello.banca_valdir.models.Fornecedor;
import com.example.paulomello.banca_valdir.R;
import com.example.paulomello.banca_valdir.utils.DatePickerDialogHelper;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class FornecedorPurchaseDialogFragment extends DialogFragment {

    public interface NoticeDialogListener {
        void onDialogPositiveClick(Compra compra, int position);
    }

    private static final String EXTRA_TITLE = "TITLE";
    public static final String EXTRA_POSITIVE_BUTTON = "POSITIVE_BUTTON";
    private static final String EXTRA_ITEM = "SERIALIZABLE_ITEM";
    private static final String EXTRA_POSITION = "ITEM_POSITION";

    private FornecedorPurchaseDialogFragment.NoticeDialogListener listener;
    private EditText valorEditText;
    private EditText dataEditText;
    private Compra currentCompra = null;
    public static Fornecedor currentFornecedor = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (FornecedorPurchaseDialogFragment.NoticeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement NoticeDialogListener");
        }
    }

    public static FornecedorPurchaseDialogFragment newInstance(String title, String positiveButton, Fornecedor fornecedor, int position) {
        FornecedorPurchaseDialogFragment dialog = new FornecedorPurchaseDialogFragment();
        currentFornecedor = fornecedor;
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TITLE, title);
        bundle.putString(EXTRA_POSITIVE_BUTTON, positiveButton);
        bundle.putSerializable(EXTRA_ITEM, fornecedor);
        bundle.putInt(EXTRA_POSITION, position);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static FornecedorPurchaseDialogFragment newInstance(String title, String positiveButton) {
        return newInstance(title, positiveButton, null, -1);
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Context context = getActivity();
        final Bundle bundle = getArguments();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(bundle.getString(EXTRA_TITLE));
        builder.setPositiveButton(bundle.getString(EXTRA_POSITIVE_BUTTON), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                currentCompra.setValor(valorEditText.getText().toString());
                currentCompra.setData_compra(dataEditText.getText().toString());
                currentCompra.setId_fornecedor(currentFornecedor.getId());
                currentCompra.setStatus(getString(R.string.aberto));
                listener.onDialogPositiveClick(currentCompra, bundle.getInt(EXTRA_POSITION));
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_create_purchase, null, true);
        builder.setView(view);

        setView(view);
        DatePickerDialogHelper.setDatePickerDialog(dataEditText, context, new SimpleDateFormat(context.getString(R.string.date_formatter), new Locale("pt", "BR")));
        return builder.create();
    }

    private void setView(View view) {
        valorEditText = view.findViewById(R.id.valor);
        dataEditText = view.findViewById(R.id.date_compra);
        currentCompra = new Compra();
    }
}
