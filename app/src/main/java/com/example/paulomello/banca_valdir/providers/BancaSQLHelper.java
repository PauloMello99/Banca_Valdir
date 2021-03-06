package com.example.paulomello.banca_valdir.providers;

import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BancaSQLHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "BancaDB";

    private static final int DATABASE_VERSION = 17;
    private Context context;


    public BancaSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(ClienteSchema.CREATE_TABLE_ITEM);
            sqLiteDatabase.execSQL(FornecedorSchema.CREATE_TABLE_ITEM);
            sqLiteDatabase.execSQL(VendaSchema.CREATE_TABLE_VENDA);
            sqLiteDatabase.execSQL(CompraSchema.CREATE_TABLE_COMPRA);
        } catch (Exception ex){
            AlertDialog builder = new AlertDialog.Builder(context).setMessage(""+ex).show();
        }
    }

    @Override
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try {
            sqLiteDatabase.execSQL(ClienteSchema.DROP_TABLE_ITEM);
            sqLiteDatabase.execSQL(FornecedorSchema.DROP_TABLE_ITEM);
            sqLiteDatabase.execSQL(VendaSchema.DROP_TABLE_VENDA);
            sqLiteDatabase.execSQL(CompraSchema.DROP_TABLE_COMPRA);
            onCreate(sqLiteDatabase);
        } catch (Exception ex){
            Log.e(DATABASE_NAME, "Error upgrade database");
        }
    }
}
