package com.example.alex.testapp.activity.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Toast;

import com.example.alex.testapp.Constants;
import com.example.alex.testapp.R;
import com.example.alex.testapp.activity.FoundProductActivity;
import com.example.alex.testapp.database.ProductsDataBase;
import com.example.alex.testapp.model.Categories;
import com.example.alex.testapp.model.Image;
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
    private ProductsDataBase productsDB;
    private NetworkInfo networkInfo;


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
    }

    @Override
    public void onResume() {
        super.onResume();
        checkInternet();

    }

    private void initResources(){
        edSearch = view.findViewById(R.id.ed_search);
        serviceRetrofit = new ServiceRetrofit();
        foundActivityIntend = new Intent(getContext(), FoundProductActivity.class);
        btnSubmit = view.findViewById(R.id.btn_submit);
        productsDB = new ProductsDataBase(getContext());
    }

    private void getListCategories(){
        Retrofit retrofit = serviceRetrofit.getResultRetrofit();
        EtsyAPI service = retrofit.create(EtsyAPI.class);
        Observable<Categories> getCategories = service.getCategories(Constants.KEY);
        getCategories
                .subscribeOn(Schedulers.io())
                .map(categories -> toCategories(categories))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categories -> {
                    Log.d("TAG", "size catList " + categories.size());
                    initSpinner(categories);

                });
    }

    private List<ResponseProduct> toResult(Product product){
        List<ResponseProduct> list = new ArrayList<>();
        for (int i = 0; i < product.getResults().size(); i++){
            list.add(product.getResults().get(i));
        }
        return list;
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
                checkCategory, searchQuery, "Images");
        getResult
                .subscribeOn(Schedulers.io())
                .map(product -> toResult(product))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(product -> {
                    productsDB.writeListDB(product);
                    List<ResponseProduct> responseProductList = product;
                    ResponseProduct responseProduct = product.get(0);
                    List<Image> imageList = responseProduct.getImages();
                    Image image = imageList.get(0);

                    Log.d("TAG", "result getListProduct " + product.size()

                            + "\n" + responseProduct
                            + "\n" + image.getUrl75x75()
                            + "\n" + imageList.size());
                });

    }

    private void initSpinner(List<String> categoriesList){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, categoriesList);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner = view.findViewById(R.id.sp_categories);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Categories");
        spinner.setSelection(0, true);
//        spinner.setSelection(1);
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

    private boolean checkInternet(){
        ConnectivityManager manager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = manager.getActiveNetworkInfo();
       try{
           if (networkInfo==null){
               List<String> cat = new ArrayList<>();
               cat.add(0, "Not Internet!");
               initSpinner(cat);
               Toast.makeText(getContext(), "Not Internet Connection!\n" +
                       "On Internet and restart App!", Toast.LENGTH_LONG).show();
               return false;
           }else
               getListCategories();
               clickSubmit();
               return true;
       }catch (Exception e){
           Log.d("TAG", "Not Internet Connection", e);
           return false;
       }


    }

    private void clickSubmit(){
        btnSubmit.setOnClickListener(listener -> {
            searchQuery = edSearch.getText().toString();
            if (searchQuery.isEmpty()){
                Toast.makeText(getContext(), "Search field is empty!",
                        Toast.LENGTH_LONG).show();
            }else {
                Log.d("TAG", "search word " + searchQuery);
                deleteDB();
                getListProduct();
                startActivity(foundActivityIntend);
            }
                }

        );
    }

    private void deleteDB() {
        ProductsDataBase productsDB = new ProductsDataBase(getContext());
        SQLiteDatabase db = productsDB.getReadableDatabase();
        db.delete(ProductsDataBase.FIND_PRODUCT_DB, null, null);
    }
}
