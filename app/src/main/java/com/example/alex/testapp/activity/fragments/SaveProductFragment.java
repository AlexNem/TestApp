package com.example.alex.testapp.activity.fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alex.testapp.R;
import com.example.alex.testapp.database.ProductsDataBase;
import com.example.alex.testapp.model.ResponseProduct;
import com.example.alex.testapp.recycler_view.ProductFragment;
import com.example.alex.testapp.recycler_view.ProductRecViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SaveProductFragment extends Fragment implements ProductFragment.OnListFragmentInteractionListener {

    private View view;
    private ProductFragment.OnListFragmentInteractionListener listener;
    private List<ResponseProduct> itemList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_save_product, container, false);
        return view;


    }

    @Override
    public void onStart() {
        super.onStart();

//        initRecycler();
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

    private void initRecycler(){
        itemList = getProductList(getContext());
        RecyclerView recyclerView = view.findViewById(R.id.rec_list);
        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(manager);
        ProductRecViewAdapter recViewAdapter = new ProductRecViewAdapter(itemList ,listener);
        recyclerView.setAdapter(recViewAdapter);
    }

    @Override
    public void onListFragmentInteraction(ResponseProduct item) {

    }
}
