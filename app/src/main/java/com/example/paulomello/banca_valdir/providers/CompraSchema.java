package com.example.paulomello.banca_valdir.providers;

public class CompraSchema {

    public static final String TABLE_COMPRA = "compra";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ID_FORNECEDOR = "id_fornecedor";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_VALOR= "valor";
    public static final String COLUMN_DATA_COMPRA= "data_compra";

    public static final String CREATE_TABLE_COMPRA = "CREATE TABLE " + TABLE_COMPRA + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ID_FORNECEDOR + " INTEGER NOT NULL, "
            + COLUMN_STATUS + " TEXT NOT NULL, "
            + COLUMN_VALOR + " DECIMAL NOT NULL, "
            + COLUMN_DATA_COMPRA + " TEXT " + ");";

    public static final String DROP_TABLE_COMPRA= "DROP TABLE IF EXISTS " + TABLE_COMPRA;

    public static final String WHERE_ID_EQUAL = VendaSchema.COLUMN_ID + " = ? ";

    public static final String SELECT_ALL_COMPRA= "SELECT * FROM " + CompraSchema.TABLE_COMPRA;

    public static final String SELECT_BY_ID_COMPRA = "SELECT * FROM " + CompraSchema.TABLE_COMPRA + " WHERE " + WHERE_ID_EQUAL;
}
