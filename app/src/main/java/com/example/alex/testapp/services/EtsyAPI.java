package com.example.alex.testapp.services;


import com.example.alex.testapp.model.Categories;
import com.example.alex.testapp.model.Product;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EtsyAPI {

    @GET("taxonomy/categories")
    Observable<Categories> getCategories(
            @Query("api_key") String api_key
    );

    @GET("listings/active")
    Observable<Product> getResult(
            @Query("api_key") String api_key,
            @Query("category") String category,
            @Query("keywords") String keywords,
            @Query("includes") String includesImages
    );
}
