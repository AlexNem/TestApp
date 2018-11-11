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

import java.util.ArrayList;
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
    public List<ResponseProduct> getFoundProductList(){
        List<ResponseProduct> list = new ArrayList<>();
        String query = "SELECT * FROM " + ProductsDataBase.FIND_PRODUCT_DB;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex(ProductsDataBase.KEY_ID));
                String url = cursor.getString(cursor.getColumnIndex(ProductsDataBase.KEY_IMAGE_URL));
                String name = cursor.getString(cursor.getColumnIndex(ProductsDataBase.KEY_NAME));
                String description = cursor.getString(cursor.getColumnIndex(ProductsDataBase.KEY_DESCRIPTION));
                String price = cursor.getString(cursor.getColumnIndex(ProductsDataBase.KEY_PRICE));
                list.add(new ResponseProduct(id, url, name, description, price));
            }while (cursor.moveToNext());
        }
        this.close();
        return list;
    }

    public void saveProduct(ResponseProduct product){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ResponseProduct responseProduct = product;
        contentValues.put(ProductsDataBase.KEY_ID,responseProduct.getListingId());
        contentValues.put(ProductsDataBase.KEY_IMAGE_URL,responseProduct.getUrl());
        contentValues.put(ProductsDataBase.KEY_NAME,responseProduct.getTitle());
        contentValues.put(ProductsDataBase.KEY_DESCRIPTION,responseProduct.getDescription());
        contentValues.put(ProductsDataBase.KEY_PRICE,responseProduct.getPrice());
        database.insert(ProductsDataBase.SAVE_PRODUCT_DB, null, contentValues);
    }

    public void deleteProduct(int listing_id){
        String query = "SELECT * FROM " + ProductsDataBase.SAVE_PRODUCT_DB;
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "_id = " + listing_id;
        Cursor cursor = db.query(ProductsDataBase.FIND_PRODUCT_DB, null, selection,
                null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                Log.d("TAG", "Delete product " );
            }while (cursor.moveToNext());
        }
        db.delete(SAVE_PRODUCT_DB, selection, null);
        db.close();
    }

    public List<ResponseProduct> getSaveProductList(){
        List<ResponseProduct> list = new ArrayList<>();
        String query = "SELECT * FROM " + ProductsDataBase.SAVE_PRODUCT_DB;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex(ProductsDataBase.KEY_ID));
                String url = cursor.getString(cursor.getColumnIndex(ProductsDataBase.KEY_IMAGE_URL));
                String name = cursor.getString(cursor.getColumnIndex(ProductsDataBase.KEY_NAME));
                String description = cursor.getString(cursor.getColumnIndex(ProductsDataBase.KEY_DESCRIPTION));
                String price = cursor.getString(cursor.getColumnIndex(ProductsDataBase.KEY_PRICE));
                list.add(new ResponseProduct(id, url, name, description, price));
            }while (cursor.moveToNext());
        }
        db.close();
        return list;
    }

    public ResponseProduct initFoundProduct(int listing_id){
        String query = "SELECT * FROM " + ProductsDataBase.FIND_PRODUCT_DB;
        ResponseProduct product = new ResponseProduct();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "_id = " + listing_id;
        String selectionArgs[] = new String[] {};
        Cursor cursor = db.query(ProductsDataBase.FIND_PRODUCT_DB, null, selection,
                null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex(ProductsDataBase.KEY_ID));
                String url = cursor.getString(cursor.getColumnIndex(ProductsDataBase.KEY_IMAGE_URL));
                String name = cursor.getString(cursor.getColumnIndex(ProductsDataBase.KEY_NAME));
                String description = cursor.getString(cursor.getColumnIndex(ProductsDataBase.KEY_DESCRIPTION));
                String price = cursor.getString(cursor.getColumnIndex(ProductsDataBase.KEY_PRICE));
                product = new ResponseProduct(id, url, name, description, price);
                Log.d("TAG", "product " + product.getListingId());
            }while (cursor.moveToNext());
        }
        db.close();
        return product;
    }


}
