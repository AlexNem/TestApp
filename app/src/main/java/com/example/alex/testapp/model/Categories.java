
package com.example.alex.testapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categories implements Serializable
{

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("results")
    @Expose
    private List<ResponseCategories> results = new ArrayList<>();
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

    public List<ResponseCategories> getResults() {
        return results;
    }

    public void setResults(List<ResponseCategories> results) {
        this.results = results;
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
