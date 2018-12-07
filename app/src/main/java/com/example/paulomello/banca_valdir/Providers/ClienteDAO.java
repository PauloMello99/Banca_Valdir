package com.example.paulomello.banca_valdir.Providers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.paulomello.banca_valdir.Models.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements SQLiteGenericDAO<Cliente> {

    private BancaSQLHelper helper;

    public ClienteDAO(Context context) {
        this.helper = new BancaSQLHelper(context);
    }

    @Override
    public long create(Cliente cliente) throws Exception {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues contentValues = getContentValues(cliente);
        long id = database.insert(ClienteSchema.TABLE_ITEM, null, contentValues);
        if (id != -1) {
            cliente.setId(id);
            Log.e(BancaSQLHelper.DATABASE_NAME, cliente.toString());
        }
        database.close();
        return id;
    }

    @Override
    public void update(Cliente cliente) throws Exception {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues contentValues = getContentValues(cliente);
        database.update(ClienteSchema.TABLE_ITEM, contentValues, ClienteSchema.WHERE_ID_EQUAL, new String[]{String.valueOf(cliente.getId())});
        database.close();
    }

    @Override
    public void delete(Cliente cliente) throws Exception {
        SQLiteDatabase database = helper.getWritableDatabase();
        database.delete(ClienteSchema.TABLE_ITEM, ClienteSchema.WHERE_ID_EQUAL, new String[]{String.valueOf(cliente.getId())});
        database.close();
    }

    @Override
    public Cliente searchById(long id) throws Exception {
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery(ClienteSchema.SELECT_BY_ID_ITEM, new String[]{String.valueOf(id)});

        if (cursor != null)
            cursor.moveToNext();

        assert cursor != null;
        Cliente cliente = getItemFromCursor(cursor);

        cursor.close();
        database.close();
        return cliente;
    }

    @Override
    public Cliente searchByName(String name) throws Exception {
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery(ClienteSchema.SELECT_BY_NAME_ITEM, new String[]{String.valueOf(name)});

        if (cursor != null)
            cursor.moveToNext();

        assert cursor != null;
        Cliente cliente = getItemFromCursor(cursor);

        cursor.close();
        database.close();
        return cliente;
    }

    @Override
    public List<Cliente> searchAll() throws Exception {
        List<Cliente> list = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery(ClienteSchema.SELECT_ALL_ITEM, null);

        if (cursor.moveToFirst()) {
            do {
                Cliente cliente = getItemFromCursor(cursor);
                list.add(cliente);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return list;
    }

    private ContentValues getContentValues(Cliente cliente) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ClienteSchema.COLUMN_NAME, cliente.getName());
        contentValues.put(ClienteSchema.COLUMN_ADDRESS, cliente.getAddress());
        contentValues.put(ClienteSchema.COLUMN_BTHDAY, cliente.getBthday());
        contentValues.put(ClienteSchema.COLUMN_ACCOUNT_NUMBER, cliente.getAccount_number());
        contentValues.put(ClienteSchema.COLUMN_ACCOUNT_AGENCY, cliente.getAccount_agency());
        return contentValues;
    }

    private Cliente getItemFromCursor(Cursor cursor){

        long id = Long.parseLong(cursor.getString(0));
        String name = cursor.getString(1);
        String address = cursor.getString(2);
        String bthday = cursor.getString(3);
        String account_number = cursor.getString(4);
        String account_agency = cursor.getString(5);

        return new Cliente(id, name,address,bthday,account_number,account_agency);
    }
}
