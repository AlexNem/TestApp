package com.example.alex.testapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.alex.testapp.R;
import com.example.alex.testapp.database.ProductsDataBase;
import com.example.alex.testapp.model.ResponseProduct;
import com.example.alex.testapp.recycler_view.ProductFragment;
import com.example.alex.testapp.recycler_view.ProductRecViewAdapter;

import java.util.List;

public class FoundProductActivity extends AppCompatActivity
implements SwipeRefreshLayout.OnRefreshListener {

    private ProductFragment.OnListFragmentInteractionListener listener;
    private List<ResponseProduct> itemList;
    private Intent productActivityIntent;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProductsDataBase productDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);

        Toast.makeText(this, "Pull to refresh!!!", Toast.LENGTH_LONG).show();
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
        productDB = new ProductsDataBase(this);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        productActivityIntent = new Intent(this, ProductActivity.class);
        listener = new ProductFragment.OnListFragmentInteractionListener() {
            @Override
            public void onListFragmentInteraction(ResponseProduct item) {
                item.getListingId();
                productActivityIntent.putExtra("key", item.getListingId());
                startActivity(productActivityIntent);
            }
        };
        itemList = getProductList(getBaseContext());

    }

    private List<ResponseProduct> getProductList(Context context){
        List<ResponseProduct> list = productDB.getFoundProductList();
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
