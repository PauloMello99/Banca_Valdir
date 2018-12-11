package com.example.paulomello.banca_valdir.providers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.paulomello.banca_valdir.models.Compra;

import java.util.ArrayList;
import java.util.List;

public class CompraDAO implements SQLiteGenericDAO<Compra> {

    private BancaSQLHelper helper;

    public CompraDAO(Context context) {
        this.helper = new BancaSQLHelper(context);
    }

    @Override
    public long create(Compra compra) {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues contentValues = getContentValues(compra);
        long id = database.insert(CompraSchema.TABLE_COMPRA, null, contentValues);
        if (id != -1) {
            compra.setId(id);
            Log.e(BancaSQLHelper.DATABASE_NAME, compra.toString());
        }
        database.close();
        return id;
    }

    @Override
    public void update(Compra compra)  {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues contentValues = getContentValues(compra);
        database.update(CompraSchema.TABLE_COMPRA, contentValues, CompraSchema.WHERE_ID_EQUAL, new String[]{String.valueOf(compra.getId())});
        database.close();
    }

    @Override
    public void delete(Compra compra) {
        SQLiteDatabase database = helper.getWritableDatabase();
        database.delete(CompraSchema.TABLE_COMPRA, CompraSchema.WHERE_ID_EQUAL, new String[]{String.valueOf(compra.getId())});
        database.close();
    }

    @Override
    public Compra searchById(long id) {
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery(CompraSchema.SELECT_BY_ID_COMPRA, new String[]{String.valueOf(id)});

        if (cursor != null)
            cursor.moveToNext();

        assert cursor != null;
        Compra compra = getItemFromCursor(cursor);

        cursor.close();
        database.close();
        return compra;
    }

    @Override
    public Compra searchByName(String name) {
        return null;
    }

    @Override
    public List<Compra> searchAll(){
        List<Compra> list = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery(CompraSchema.SELECT_ALL_COMPRA, null);

        if (cursor.moveToFirst()) {
            do {
                Compra compra = getItemFromCursor(cursor);
                list.add(compra);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return list;
    }

    private ContentValues getContentValues(Compra compra) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CompraSchema.COLUMN_ID_FORNECEDOR, compra.getId_fornecedor());
        contentValues.put(CompraSchema.COLUMN_STATUS,compra.getStatus());
        contentValues.put(CompraSchema.COLUMN_VALOR,compra.getValor());
        contentValues.put(CompraSchema.COLUMN_DATA_COMPRA,compra.getData_compra());
        return contentValues;
    }

    private Compra getItemFromCursor(Cursor cursor){

        long id = Long.parseLong(cursor.getString(0));
        long id_fornecedor = Long.parseLong(cursor.getString(1));
        String status = cursor.getString(2);
        String valor = cursor.getString(3);
        String data_compra= cursor.getString(4);

        return new Compra(id,id_fornecedor,status,valor,data_compra);
    }

}
