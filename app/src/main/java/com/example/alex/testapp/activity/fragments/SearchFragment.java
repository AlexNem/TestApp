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
import android.widget.EditText;
import android.widget.Spinner;

import com.example.alex.testapp.Constants;
import com.example.alex.testapp.R;
import com.example.alex.testapp.activity.FoundProductActivity;
import com.example.alex.testapp.model.Categories;
import com.example.alex.testapp.model.Product;
import com.example.alex.testapp.model.ResponseProduct;
import com.example.alex.testapp.services.EtsyAPI;
import com.example.alex.testapp.services.ServiceRetrofit;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SearchFragment extends Fragment {

    private View view;
    private Intent foundActivityIntend;
    private Button btnSubmit;
    private ServiceRetrofit serviceRetrofit;
    private String checkCategory;
    private String searchQuery;
    private EditText edSearch;
    private Spinner spinner;

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
        clickSubmit();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initResources(){
        edSearch = view.findViewById(R.id.ed_search);
        serviceRetrofit = new ServiceRetrofit();
        foundActivityIntend = new Intent(getContext(), FoundProductActivity.class);
        btnSubmit = view.findViewById(R.id.btn_submit);
    }

    private void getListCategories(){
        Retrofit retrofit = serviceRetrofit.getCategoriesRetrofit();
        EtsyAPI service = retrofit.create(EtsyAPI.class);
        Observable<Categories> getCategories = service.getCategories(Constants.KEY);
        getCategories
                .subscribeOn(Schedulers.io())
                .map(categories -> toCategories(categories))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categories -> {
                    initSpinner(categories);

                });
    }

    private List<String> toCategories(Categories categories){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < categories.getResults().size(); i++){
            list.add(categories.getResults().get(i).getCategoryName());
        }
        return list;
    }

    private void getListProduct(){
        Log.d("TAG", "start getListProduct ");
        Retrofit retrofit = serviceRetrofit.getResultRetrofit();
        EtsyAPI service = retrofit.create(EtsyAPI.class);
        Observable<Product> getResult = service.getResult(Constants.KEY,
                checkCategory, searchQuery);
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

    private void initSpinner(List<String> categoriesList){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, categoriesList);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner = view.findViewById(R.id.sp_categories);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Categories");
        spinner.setSelection(21);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                checkCategory = spinner.getSelectedItem().toString();
                Log.d("TAG", "check " + checkCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void clickSubmit(){
        btnSubmit.setOnClickListener(listener -> {
                    searchQuery = edSearch.getText().toString();
                    Log.d("TAG", "search word " + searchQuery);
                    getListProduct();
                    startActivity(foundActivityIntend);
                }

        );
    }
}
