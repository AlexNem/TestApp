package com.example.alex.testapp.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alex.testapp.R;
import com.example.alex.testapp.database.ProductsDataBase;
import com.example.alex.testapp.model.ResponseProduct;
import com.squareup.picasso.Picasso;

public class ProductActivity extends AppCompatActivity {

    private int listing_id;
    private int save_id;
    private ImageView imageProduct;
    private TextView name;
    private TextView description;
    private TextView price;
    private ResponseProduct product;
    private ProductsDataBase productsDB;
    private Button btnDelete;
    private Button btnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        initResources();
        if (listing_id==0){
            initSaveProduct(save_id);
        }else {
            initFoundProduct(listing_id);
        }
        onClick();
        setActivity();
    }

    private void setActivity(){
        Picasso.with(this)
                .load(product.getUrl())
                .into(imageProduct);
        name.setText(product.getTitle());
        description.setText(product.getDescription());
        price.setText(product.getPrice());
    }

    private void initFoundProduct(int listing_id){
        String query = "SELECT * FROM " + ProductsDataBase.FIND_PRODUCT_DB;
        productsDB = new ProductsDataBase(this);
        SQLiteDatabase db = productsDB.getReadableDatabase();
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
        productsDB.close();
    }

    private void initSaveProduct(int save_id){
        String query = "SELECT * FROM " + ProductsDataBase.FIND_PRODUCT_DB;
        productsDB = new ProductsDataBase(this);
        SQLiteDatabase db = productsDB.getReadableDatabase();
        String selection = "_id = " + save_id;
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
        productsDB.close();
    }

    private void initResources(){
        btnDelete = findViewById(R.id.btn_delete);
        btnSave = findViewById(R.id.btn_save);
        save_id = getIntent().getIntExtra("key", 0);
        listing_id = getIntent().getIntExtra("key", 0);
        Log.d("TAG", "linked ID " + listing_id);
        imageProduct = findViewById(R.id.iv_product);
        name = findViewById(R.id.product_name);
        description = findViewById(R.id.product_description);
        price = findViewById(R.id.product_price);

    }

    private void onClick(){
        btnSave.setOnClickListener(listener ->{
            productsDB.saveProduct(product);
            Toast.makeText(this, "Add to Save Product!", Toast.LENGTH_SHORT).show();
        });
        btnDelete.setOnClickListener(listener ->{
            Toast.makeText(this, "Delete from Save Product!", Toast.LENGTH_SHORT).show();
            productsDB.deleteProduct(save_id);
        });
    }
}
