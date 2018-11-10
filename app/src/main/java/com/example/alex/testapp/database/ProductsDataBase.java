package com.example.alex.testapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.alex.testapp.model.Image;
import com.example.alex.testapp.model.ResponseProduct;

import java.util.List;

public class ProductsDataBase extends SQLiteOpenHelper {

    public static final String FIND_PRODUCT_DB = "Find_product";
    public static final String SAVE_PRODUCT_DB = "Save_product";
    public static final int DB_VERSION = 1;

    public static final String KEY_ID = "_id";
    public static final String KEY_IMAGE_URL = "imageURL";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_PRICE = "price";


    public ProductsDataBase(@Nullable Context context) {
        super(context, FIND_PRODUCT_DB, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + FIND_PRODUCT_DB + "("
                + KEY_ID + " integer primary key,"
                + KEY_IMAGE_URL + " text,"
                + KEY_NAME + " text,"
                + KEY_DESCRIPTION + " text,"
                + KEY_PRICE + " text" + ")");
        db.execSQL("create table " + SAVE_PRODUCT_DB + "("
                + KEY_ID + " integer primary key,"
                + KEY_IMAGE_URL + " text,"
                + KEY_NAME + " text,"
                + KEY_DESCRIPTION + " text,"
                + KEY_PRICE + " text" + ")"

        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + FIND_PRODUCT_DB);
        db.execSQL("drop table if exists " + SAVE_PRODUCT_DB);
        onCreate(db);
    }

    public void writeListDB(List<ResponseProduct> productList){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (int i=0; i<productList.size(); i++){
            ResponseProduct responseProduct = productList.get(i);
            List<Image> imageList = responseProduct.getImages();
            Image image = imageList.get(0);
            contentValues.put(ProductsDataBase.KEY_ID,responseProduct.getListingId());
            contentValues.put(ProductsDataBase.KEY_IMAGE_URL,image.getUrlFullxfull());
            contentValues.put(ProductsDataBase.KEY_NAME,responseProduct.getTitle());
            contentValues.put(ProductsDataBase.KEY_DESCRIPTION,responseProduct.getDescription());
            contentValues.put(ProductsDataBase.KEY_PRICE,responseProduct.getPrice());
            database.insert(ProductsDataBase.FIND_PRODUCT_DB, null, contentValues);

        }

    }
    public void readListDB(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(ProductsDataBase.FIND_PRODUCT_DB, null, null, null, null, null, null);

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

    public void saveProduct(ResponseProduct product){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ResponseProduct responseProduct = product;
        List<Image> imageList = responseProduct.getImages();
        Image image = imageList.get(0);
        contentValues.put(ProductsDataBase.KEY_ID,responseProduct.getListingId());
        contentValues.put(ProductsDataBase.KEY_IMAGE_URL,image.getUrl75x75());
        contentValues.put(ProductsDataBase.KEY_NAME,responseProduct.getTitle());
        contentValues.put(ProductsDataBase.KEY_DESCRIPTION,responseProduct.getDescription());
        contentValues.put(ProductsDataBase.KEY_PRICE,responseProduct.getPrice());
        database.insert(ProductsDataBase.SAVE_PRODUCT_DB, null, contentValues);
    }

    public void readProduct(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(ProductsDataBase.SAVE_PRODUCT_DB, null, null, null, null, null, null);
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
