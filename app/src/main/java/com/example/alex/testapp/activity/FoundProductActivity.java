package com.example.alex.testapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alex.testapp.R;
import com.example.alex.testapp.recycler_view.ProductFragment;
import com.example.alex.testapp.recycler_view.ProductRecViewAdapter;
import com.example.alex.testapp.recycler_view.dummy.DummyContent;

public class FoundProductActivity extends AppCompatActivity implements ProductFragment.OnListFragmentInteractionListener {

    private ProductFragment.OnListFragmentInteractionListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);

        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.rec_list);
        GridLayoutManager manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);
        ProductRecViewAdapter recViewAdapter = new ProductRecViewAdapter(DummyContent.ITEMS ,listener);
        recyclerView.setAdapter(recViewAdapter);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
