package com.example.alex.testapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.alex.testapp.model.ResponseProduct;

import java.util.List;

public class ProductsDataBase extends SQLiteOpenHelper {

    public static final String DB_NAME = ProductsDataBase.class.getSimpleName();
    public static final int DB_VERSION = 1;
    public static final String DB_FIELD = "products";

    public static final String KEY_ID = "_id";
    public static final String KEY_IMAGE_URL = "imageURL";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_PRICE = "price";


    public ProductsDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DB_FIELD + "("
                + KEY_ID + " integer primary key,"
                + KEY_IMAGE_URL + " text,"
                + KEY_NAME + " text,"
                + KEY_DESCRIPTION + " text,"
                + KEY_PRICE + " text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + DB_FIELD);
        onCreate(db);
    }

    public void writeDB(List<ResponseProduct> productList){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (int i=0; i<productList.size(); i++){
            ResponseProduct responseProduct = productList.get(i);
            contentValues.put(ProductsDataBase.KEY_ID,responseProduct.getListingId());
            contentValues.put(ProductsDataBase.KEY_IMAGE_URL,responseProduct.getUrl());
            contentValues.put(ProductsDataBase.KEY_NAME,responseProduct.getTitle());
            contentValues.put(ProductsDataBase.KEY_DESCRIPTION,responseProduct.getDescription());
            contentValues.put(ProductsDataBase.KEY_PRICE,responseProduct.getPrice());
            database.insert(ProductsDataBase.DB_FIELD, null, contentValues);
            readDB();
        }

    }
    public void readDB(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(ProductsDataBase.DB_FIELD, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(ProductsDataBase.KEY_ID);
            int urlIndex = cursor.getColumnIndex(ProductsDataBase.KEY_IMAGE_URL);
            int nameIndex = cursor.getColumnIndex(ProductsDataBase.KEY_NAME);
            int descIndex = cursor.getColumnIndex(ProductsDataBase.KEY_DESCRIPTION);
            int priceIndex = cursor.getColumnIndex(ProductsDataBase.KEY_PRICE);
            do {
                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", url = " + cursor.getString(urlIndex) +
                        ", name = " + cursor.getString(nameIndex) +
                        ", description = " + cursor.getString(descIndex) +
                        ", price = " + cursor.getString(priceIndex));
            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 rows");

        cursor.close();
    }
}
