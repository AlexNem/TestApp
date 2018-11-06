package com.example.alex.testapp.activity.fragments;

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
import com.example.alex.testapp.recycler_view.ProductFragment;
import com.example.alex.testapp.recycler_view.ProductRecViewAdapter;
import com.example.alex.testapp.recycler_view.dummy.DummyContent;

public class SaveProductFragment extends Fragment implements ProductFragment.OnListFragmentInteractionListener {

    private View view;
    private ProductFragment.OnListFragmentInteractionListener listener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_save_product, container, false);
        return view;


    }

    @Override
    public void onStart() {
        super.onStart();

        initRecycler();
    }

    private void initRecycler(){
        RecyclerView recyclerView = view.findViewById(R.id.rec_list);
        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(manager);
        ProductRecViewAdapter recViewAdapter = new ProductRecViewAdapter(DummyContent.ITEMS ,listener);
        recyclerView.setAdapter(recViewAdapter);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
