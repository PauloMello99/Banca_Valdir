package com.example.paulomello.banca_valdir.Providers;

public class VendaSchema {

    public static final String TABLE_VENDA = "venda";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ID_CLIENTE = "id_cliente";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_VALOR= "valor";
    public static final String COLUMN_DATA_VENDA= "data_venda";
    public static final String TABLE_COLUMN_ID= VendaSchema.TABLE_VENDA+"."+COLUMN_ID;
    public static final String TABLE_COLUMN_VALOR= VendaSchema.TABLE_VENDA+"."+COLUMN_VALOR;
    public static final String TABLE_COLUMN_STATUS= VendaSchema.TABLE_VENDA+"."+COLUMN_STATUS;
    public static final String TABLE_COLUMN_DATA_VENDA= VendaSchema.TABLE_VENDA+"."+COLUMN_DATA_VENDA;

    public static final String CREATE_TABLE_VENDA = "CREATE TABLE " + TABLE_VENDA + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ID_CLIENTE + " INTEGER NOT NULL, "
            + COLUMN_STATUS + " TEXT NOT NULL, "
            + COLUMN_VALOR + " DECIMAL(5,2) NOT NULL, "
            + COLUMN_DATA_VENDA + " TEXT " + ");";

    public static final String DROP_TABLE_VENDA= "DROP TABLE IF EXISTS " + TABLE_VENDA;

    public static final String WHERE_ID_EQUAL = VendaSchema.COLUMN_ID + " = ? ";

    public static final String SELECT_VENDA_TO_CARD = "SELECT "+ ClienteSchema.TABLE_COLUMN_NAME+","+ TABLE_COLUMN_VALOR
            + " FROM " + VendaSchema.TABLE_VENDA
            + "INNER JOIN " + ClienteSchema.TABLE_ITEM
            + " ON " + TABLE_COLUMN_ID + "=" + ClienteSchema.TABLE_COLUMN_ID;

    public static final String SELECT_ALL_VENDA = "SELECT "+ ClienteSchema.TABLE_COLUMN_NAME+","
            + TABLE_COLUMN_STATUS+","
            + TABLE_COLUMN_VALOR+ ","
            + TABLE_COLUMN_DATA_VENDA
            + " FROM " + VendaSchema.TABLE_VENDA
            + "INNER JOIN " + ClienteSchema.TABLE_ITEM
            + " ON " + TABLE_COLUMN_ID + "=" + ClienteSchema.TABLE_COLUMN_ID;

    public static final String SELECT_BY_ID_VENDA = "SELECT * FROM " + VendaSchema.TABLE_VENDA + " WHERE " + WHERE_ID_EQUAL;
}
