package com.example.alex.testapp.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.alex.testapp.R;
import com.example.alex.testapp.database.ProductsDataBase;
import com.example.alex.testapp.model.ResponseProduct;
import com.example.alex.testapp.recycler_view.ProductFragment;
import com.example.alex.testapp.recycler_view.ProductRecViewAdapter;
import com.example.alex.testapp.recycler_view.dummy.ProductContent;

import java.util.ArrayList;
import java.util.List;

public class FoundProductActivity extends AppCompatActivity
implements SwipeRefreshLayout.OnRefreshListener {

    private ProductFragment.OnListFragmentInteractionListener listener;
    private List<ResponseProduct> mockList;
    private List<ResponseProduct> itemList;
    private Intent productActivityIntent;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);

        initResources();
        initRecyclerView();
    }



    private void getData(){
        swipeRefreshLayout.setRefreshing(true);
        Log.d("TAG", "det Product");
        initResources();
        initRecyclerView();
        swipeRefreshLayout.setRefreshing(false);
    }
    @Override
    public void onRefresh() {
        getData();
    }

    private void initResources(){
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        productActivityIntent = new Intent(this, ProductActivity.class);
        listener = new ProductFragment.OnListFragmentInteractionListener() {
            @Override
            public void onListFragmentInteraction(ResponseProduct item) {
                startActivity(productActivityIntent);
            }
        };
        itemList = getProductList(getBaseContext());
        mockList = new ArrayList<>();
        mockList.add(0, new ResponseProduct(1, "content",
                "details", "description", "21"));
        mockList.add(1, new ResponseProduct(2, "content",
                "details", "description1", "32"));
        mockList.add(2, new ResponseProduct(3, "content",
                "details", "description2", "46"));

    }

    private List<ResponseProduct> getProductList(Context context){
        List<ResponseProduct> list = new ArrayList<>();
        String query = "SELECT * FROM " + ProductsDataBase.DB_FIELD;
        ProductsDataBase productsDB = new ProductsDataBase(context);
        SQLiteDatabase db = productsDB.getWritableDatabase();
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
        productsDB.close();
        return list;
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.rec_list);
        GridLayoutManager manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);
        ProductRecViewAdapter recViewAdapter = new ProductRecViewAdapter(itemList,listener);
        recyclerView.setAdapter(recViewAdapter);
    }

}
