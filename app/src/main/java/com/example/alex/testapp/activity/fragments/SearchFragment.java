package com.example.alex.testapp.activity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.alex.testapp.Constants;
import com.example.alex.testapp.R;
import com.example.alex.testapp.activity.FoundProductActivity;
import com.example.alex.testapp.model.Categories;
import com.example.alex.testapp.model.Result;
import com.example.alex.testapp.services.EtsyAPI;
import com.example.alex.testapp.services.ServiceRetrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SearchFragment extends Fragment {

    private String[] categoriesData = {"One", "2", "3", "Four", "5"};
    private View view;
    private Intent foundActivityIntend;
    private Button btnSubmit;
    private ServiceRetrofit serviceRetrofit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        initSpinner();
        initResources();
        clickSubmit();
        getCategories();
        getResult();
    }

    private void getCategories(){
        Retrofit retrofit = serviceRetrofit.getCategoriesRetrofit();
        EtsyAPI service = retrofit.create(EtsyAPI.class);
        Observable<Categories> getCategories = service.getCategories(Constants.KEY);
        getCategories
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categories -> {
                    Log.d("TAG", "result getCategories " + categories.getCount());
                });
    }

    private void getResult(){
        Retrofit retrofit = serviceRetrofit.getResultRetrofit();
        EtsyAPI service = retrofit.create(EtsyAPI.class);
        Observable<Result> getResult = service.getResult(Constants.KEY,
                "paper_goods", "terminator");
        getResult
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {

                    Log.d("TAG", "result getResult " + result);
                });
    }

    private void initSpinner(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, categoriesData);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        Spinner spinner = view.findViewById(R.id.sp_categories);
        spinner.setAdapter(adapter);

        spinner.setPrompt("Categories");

        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initResources(){
        serviceRetrofit = new ServiceRetrofit();
        foundActivityIntend = new Intent(getContext(), FoundProductActivity.class);
        btnSubmit = view.findViewById(R.id.btn_submit);
    }

    private void clickSubmit(){
        btnSubmit.setOnClickListener(listener -> startActivity(foundActivityIntend));
    }
}
