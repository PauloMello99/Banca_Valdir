package com.example.paulomello.banca_valdir.providers;

public class ClienteSchema {

    private ClienteSchema(){}

    public static final String TABLE_ITEM = "cliente";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_BTHDAY= "birthday";
    public static final String COLUMN_ACCOUNT_NUMBER= "account";
    public static final String COLUMN_ACCOUNT_AGENCY= "agency";
    public static final String TABLE_COLUMN_ID= ClienteSchema.TABLE_ITEM+"."+COLUMN_ID;
    public static final String TABLE_COLUMN_NAME= ClienteSchema.TABLE_ITEM+"."+COLUMN_NAME;

    public static final String CREATE_TABLE_ITEM = "CREATE TABLE " + TABLE_ITEM + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_ADDRESS + " TEXT NOT NULL, "
            + COLUMN_BTHDAY + " TEXT NOT NULL, "
            + COLUMN_ACCOUNT_NUMBER + " TEXT, "
            + COLUMN_ACCOUNT_AGENCY + " TEXT " + ");";

    public static final String DROP_TABLE_ITEM = "DROP TABLE IF EXISTS " + TABLE_ITEM;

    public static final String WHERE_ID_EQUAL = ClienteSchema.TABLE_COLUMN_ID + " = ?";

    public static final String WHERE_NAME= ClienteSchema.COLUMN_NAME + "LIKE %?% ";

    public static final String SELECT_ALL_ITEM = "SELECT * FROM " + ClienteSchema.TABLE_ITEM;

    public static final String SELECT_BY_ID_ITEM = "SELECT * FROM " + ClienteSchema.TABLE_ITEM + " WHERE " + WHERE_ID_EQUAL;

    public static final String SELECT_BY_NAME_ITEM = "SELECT * FROM " + ClienteSchema.TABLE_ITEM + " WHERE " + WHERE_NAME;

}
