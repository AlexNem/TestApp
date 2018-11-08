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
import com.example.alex.testapp.model.Product;
import com.example.alex.testapp.model.ResponseCategories;
import com.example.alex.testapp.model.ResponseProduct;
import com.example.alex.testapp.services.EtsyAPI;
import com.example.alex.testapp.services.ServiceRetrofit;

import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SearchFragment extends Fragment {

    private List<String> categoriesList;
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
        initResources();
        getListCategories();
        initSpinner();
        clickSubmit();
        getListProduct();
    }

    private void initResources(){
        serviceRetrofit = new ServiceRetrofit();
        categoriesList = new ArrayList<>();
        foundActivityIntend = new Intent(getContext(), FoundProductActivity.class);
        btnSubmit = view.findViewById(R.id.btn_submit);
    }

    private void getListCategories(){
        Retrofit retrofit = serviceRetrofit.getCategoriesRetrofit();
        EtsyAPI service = retrofit.create(EtsyAPI.class);
        Observable<Categories> getCategories = service.getCategories(Constants.KEY);
        getCategories
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categories -> {

                    List<ResponseCategories> resultList = categories.getResults();
                    ResponseCategories result = resultList.get(1);
                    ResponseCategories result1 = resultList.get(2);


                        Log.d("TAG", "result getListCategories \n" + result.getPageDescription()
                        + "\n" + resultList.size()
                        + "\n" + result1.getCategoryName());

                });
    }

    private void getListProduct(){
        Retrofit retrofit = serviceRetrofit.getResultRetrofit();
        EtsyAPI service = retrofit.create(EtsyAPI.class);
        Observable<Product> getResult = service.getResult(Constants.KEY,
                "paper_goods", "terminator");
        getResult
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(product -> {

                    String responseCategories = product.getType();
                    List<ResponseProduct> responseProductList = product.getResponseProducts();

                    Log.d("TAG", "result getListProduct " + product.getCount()

                            + "\n" + responseCategories
                            + "\n" + responseProductList.size());
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

    private void setCategories(){

    }

    private void clickSubmit(){
        btnSubmit.setOnClickListener(listener -> startActivity(foundActivityIntend));
    }
}
