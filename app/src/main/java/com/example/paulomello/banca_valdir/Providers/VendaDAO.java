package com.example.paulomello.banca_valdir.Providers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.paulomello.banca_valdir.Models.Cliente;
import com.example.paulomello.banca_valdir.Models.Venda;

import java.util.ArrayList;
import java.util.List;

public class VendaDAO implements SQLiteGenericDAO<Venda> {

    private BancaSQLHelper helper;

    public VendaDAO(Context context) {
        this.helper = new BancaSQLHelper(context);
    }

    @Override
    public long create(Venda venda) throws Exception {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues contentValues = getContentValues(venda);
        long id = database.insert(VendaSchema.TABLE_VENDA, null, contentValues);
        if (id != -1) {
            venda.setId(id);
            Log.e(BancaSQLHelper.DATABASE_NAME, venda.toString());
        }
        database.close();
        return id;
    }

    @Override
    public void update(Venda venda) throws Exception {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues contentValues = getContentValues(venda);
        database.update(VendaSchema.TABLE_VENDA, contentValues, VendaSchema.WHERE_ID_EQUAL, new String[]{String.valueOf(venda.getId())});
        database.close();
    }

    @Override
    public void delete(Venda venda) throws Exception {
        SQLiteDatabase database = helper.getWritableDatabase();
        database.delete(VendaSchema.TABLE_VENDA, ClienteSchema.WHERE_ID_EQUAL, new String[]{String.valueOf(venda.getId())});
        database.close();
    }

    @Override
    public Venda searchById(long id) throws Exception {
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery(VendaSchema.SELECT_BY_ID_VENDA, new String[]{String.valueOf(id)});

        if (cursor != null)
            cursor.moveToNext();

        assert cursor != null;
        Venda venda = getItemFromCursor(cursor);

        cursor.close();
        database.close();
        return venda;
    }

    @Override
    public Venda searchByName(String name) throws Exception {
        return null;
    }

    @Override
    public List<Venda> searchAll() throws Exception {
        List<Venda> list = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery(VendaSchema.SELECT_ALL_VENDA, null);

        if (cursor.moveToFirst()) {
            do {
                Venda venda = getItemFromCursor(cursor);
                list.add(venda);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return list;
    }

    private ContentValues getContentValues(Venda venda) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ClienteSchema.TABLE_COLUMN_NAME, venda.getId_cliente());
        contentValues.put(VendaSchema.COLUMN_STATUS,venda.getStatus());
        contentValues.put(VendaSchema.COLUMN_VALOR,venda.getValor());
        contentValues.put(VendaSchema.COLUMN_DATA_VENDA,venda.getData_venda());
        return contentValues;
    }

    private Venda getItemFromCursor(Cursor cursor){

        long id = Long.parseLong(cursor.getString(0));
        long id_cliente = Long.parseLong(cursor.getString(1));
        String status = cursor.getString(2);
        String valor = cursor.getString(3);
        String data_venda = cursor.getString(4);

        return new Venda(id,id_cliente,status,valor,data_venda);
    }

}
