package com.example.alex.testapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.example.alex.testapp.R;
import com.example.alex.testapp.recycler_view.ProductFragment;
import com.example.alex.testapp.recycler_view.ProductRecViewAdapter;
import com.example.alex.testapp.recycler_view.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

public class FoundProductActivity extends AppCompatActivity {

    private ProductFragment.OnListFragmentInteractionListener listener;
    private List<DummyContent.DummyItem> list;
    private Intent productActivityIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);

        initResources();
        initRecyclerView();
    }

    private void initResources(){
        productActivityIntent = new Intent(this, ProductActivity.class);
        listener = new ProductFragment.OnListFragmentInteractionListener() {
            @Override
            public void onListFragmentInteraction(DummyContent.DummyItem item) {
                startActivity(productActivityIntent);
            }
        };

        list = new ArrayList<>();
        list.add(0, new DummyContent.DummyItem("1", "content",
                "details"));
        list.add(1, new DummyContent.DummyItem("2", "content1",
                "details1"));
        list.add(2, new DummyContent.DummyItem("3", "content2",
                "details2"));
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.rec_list);
        GridLayoutManager manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);
        ProductRecViewAdapter recViewAdapter = new ProductRecViewAdapter(list,listener);
        recyclerView.setAdapter(recViewAdapter);
    }

}
