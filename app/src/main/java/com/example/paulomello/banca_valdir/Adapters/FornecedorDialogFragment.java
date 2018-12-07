package com.example.paulomello.banca_valdir.Adapters;

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
import android.widget.Toast;

import com.example.paulomello.banca_valdir.Models.Fornecedor;
import com.example.paulomello.banca_valdir.R;
import com.example.paulomello.banca_valdir.Utils.DatePickerDialogHelper;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class FornecedorDialogFragment extends DialogFragment {

    public interface NoticeDialogListener {
        void onDialogPositiveClick(Fornecedor fornecedor, int position);
    }

    public static final String CREATE_TAG = "CREATE";
    public static final String EDIT_TAG = "EDIT";
    private static final String EXTRA_TITLE = "TITLE";
    public static final String EXTRA_POSITIVE_BUTTON = "POSITIVE_BUTTON";
    private static final String EXTRA_ITEM = "SERIALIZABLE_ITEM";
    private static final String EXTRA_POSITION = "ITEM_POSITION";

    private NoticeDialogListener listener;
    private EditText nameEditText;
    private EditText cadastroEditText;
    private EditText birthdayEditText;
    private EditText account_numberEditText;
    private EditText account_agencyEditText;

    private Fornecedor currentFornecedor = null;

    public FornecedorDialogFragment(){}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (NoticeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement NoticeDialogListener");
        }
    }



    public static FornecedorDialogFragment newInstance(String title, String positiveButton, Fornecedor fornecedor, int position) {
        FornecedorDialogFragment dialog = new FornecedorDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TITLE, title);
        bundle.putString(EXTRA_POSITIVE_BUTTON, positiveButton);
        bundle.putSerializable(EXTRA_ITEM, fornecedor);
        bundle.putInt(EXTRA_POSITION, position);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static FornecedorDialogFragment newInstance(String title, String positiveButton) {
        return newInstance(title, positiveButton, null, -1);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Context context = getActivity();
        final Bundle bundle = getArguments();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(bundle.getString(EXTRA_TITLE));
        builder.setPositiveButton(bundle.getString(EXTRA_POSITIVE_BUTTON), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                currentFornecedor.setName(nameEditText.getText().toString());
                currentFornecedor.setCadastro(cadastroEditText.getText().toString());
                currentFornecedor.setBthday(birthdayEditText.getText().toString());
                currentFornecedor.setAccount_number(account_numberEditText.getText().toString());
                currentFornecedor.setAccount_agency(account_agencyEditText.getText().toString());
                if(checkFields())
                    listener.onDialogPositiveClick(currentFornecedor, bundle.getInt(EXTRA_POSITION));
                else
                    Toast.makeText(context,"Campos inválidos...",Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_create_fornecedor, null, true);
        builder.setView(view);

        setView(view, bundle);
        DatePickerDialogHelper.setDatePickerDialog(birthdayEditText, context, new SimpleDateFormat(context.getString(R.string.date_formatter), new Locale("pt", "BR")));
        return builder.create();
    }

    private void setView(View view, final Bundle bundle) {
        nameEditText = view.findViewById(R.id.name);
        cadastroEditText = view.findViewById(R.id.cadastro);
        birthdayEditText = view.findViewById(R.id.birthday);
        account_numberEditText = view.findViewById(R.id.account_number);
        account_agencyEditText = view.findViewById(R.id.account_agency);

        if (getTag().equals(EDIT_TAG)) {
            currentFornecedor = (Fornecedor) bundle.getSerializable(EXTRA_ITEM);
            nameEditText.setText(currentFornecedor.getName());
            cadastroEditText.setText(currentFornecedor.getCadastro());
            birthdayEditText.setText(currentFornecedor.getBthday());
            account_numberEditText.setText(currentFornecedor.getAccount_number());
            account_agencyEditText.setText(currentFornecedor.getAccount_agency());
        } else {
            currentFornecedor = new Fornecedor();
        }
    }

    public boolean checkFields(){
        boolean check = true;
        if(currentFornecedor.getName().isEmpty())
        {
            nameEditText.setError("Insira o nome do fornecedor");
            check = false;
        }
        if(currentFornecedor.getBthday().isEmpty())
        {
            birthdayEditText.setError("Insira a data de nascimento do fornecedor");
            check = false;
        }
        if(currentFornecedor.getCadastro().isEmpty())
        {
            cadastroEditText.setError("Insira o CPF/CNPJ do fornecedor");
        }
        return check;
    }
}
