package com.example.alex.testapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("responseProducts")
    @Expose
    private List<ResponseProduct> responseProducts = new ArrayList<>();
    @SerializedName("params")
    @Expose
    private Params params;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ResponseProduct> getResponseProducts() {
        return responseProducts;
    }

    public void setResponseProducts(List<ResponseProduct> responseProducts) {
        this.responseProducts = responseProducts;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
