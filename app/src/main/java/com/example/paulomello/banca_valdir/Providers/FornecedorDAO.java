package com.example.paulomello.banca_valdir.Providers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.paulomello.banca_valdir.Models.Fornecedor;

import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO implements SQLiteGenericDAO<Fornecedor>{

    private BancaSQLHelper helper;

    public FornecedorDAO(Context context) {
        this.helper = new BancaSQLHelper(context);
    }

    @Override
    public long create(Fornecedor fornecedor) throws Exception {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues contentValues = getContentValues(fornecedor);
        long id = database.insert(FornecedorSchema.TABLE_ITEM, null, contentValues);
        if (id != -1) {
            fornecedor.setId(id);
            Log.e(BancaSQLHelper.DATABASE_NAME, fornecedor.toString());
        }
        database.close();
        return id;
    }

    @Override
    public void update(Fornecedor fornecedor) throws Exception {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues contentValues = getContentValues(fornecedor);
        database.update(FornecedorSchema.TABLE_ITEM, contentValues, FornecedorSchema.WHERE_ID_EQUAL, new String[]{String.valueOf(fornecedor.getId())});
        database.close();
    }

    @Override
    public void delete(Fornecedor fornecedor) throws Exception {
        SQLiteDatabase database = helper.getWritableDatabase();
        database.delete(FornecedorSchema.TABLE_ITEM, FornecedorSchema.WHERE_ID_EQUAL, new String[]{String.valueOf(fornecedor.getId())});
        database.close();
    }

    @Override
    public Fornecedor searchById(long id) throws Exception {
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery(FornecedorSchema.SELECT_BY_ID_ITEM, new String[]{String.valueOf(id)});
        if (cursor != null)
            cursor.moveToNext();
        assert cursor != null;
        Fornecedor fornecedor = getItemFromCursor(cursor);
        cursor.close();
        database.close();
        return fornecedor;
    }

    @Override
    public Fornecedor searchByName(String name) throws Exception {
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery(FornecedorSchema.SELECT_BY_NAME_ITEM, new String[]{String.valueOf(name)});
        if (cursor != null)
            cursor.moveToNext();
        assert cursor != null;
        Fornecedor fornecedor = getItemFromCursor(cursor);
        cursor.close();
        database.close();
        return fornecedor;
    }

    @Override
    public List<Fornecedor> searchAll() throws Exception {
        List<Fornecedor> list = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery(FornecedorSchema.SELECT_ALL_ITEM, null);
        if (cursor.moveToFirst()) {
            do {
                Fornecedor Fornecedor = getItemFromCursor(cursor);
                list.add(Fornecedor);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return list;
    }

    private ContentValues getContentValues(Fornecedor fornecedor) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FornecedorSchema.COLUMN_NAME, fornecedor.getName());
        contentValues.put(FornecedorSchema.COLUMN_CADASTRO, fornecedor.getCadastro());
        contentValues.put(FornecedorSchema.COLUMN_BTHDAY, fornecedor.getBthday());
        contentValues.put(FornecedorSchema.COLUMN_ACCOUNT_NUMBER, fornecedor.getAccount_number());
        contentValues.put(FornecedorSchema.COLUMN_ACCOUNT_AGENCY, fornecedor.getAccount_agency());
        return contentValues;
    }

    private Fornecedor getItemFromCursor(Cursor cursor){

        long id = Long.parseLong(cursor.getString(0));
        String name = cursor.getString(1);
        String cadastro = cursor.getString(2);
        String bthday = cursor.getString(3);
        String account_number = cursor.getString(4);
        String account_agency = cursor.getString(5);

        return new Fornecedor(id, name,cadastro,bthday,account_number,account_agency);
    }

}
