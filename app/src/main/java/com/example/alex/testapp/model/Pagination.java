
package com.example.alex.testapp.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagination implements Serializable
{

    @SerializedName("effective_limit")
    @Expose
    private int effectiveLimit;
    @SerializedName("effective_offset")
    @Expose
    private int effectiveOffset;
    @SerializedName("next_offset")
    @Expose
    private int nextOffset;
    @SerializedName("effective_page")
    @Expose
    private int effectivePage;
    @SerializedName("next_page")
    @Expose
    private int nextPage;
    private final static long serialVersionUID = -8042364636819275615L;

    public int getEffectiveLimit() {
        return effectiveLimit;
    }

    public void setEffectiveLimit(int effectiveLimit) {
        this.effectiveLimit = effectiveLimit;
    }

    public int getEffectiveOffset() {
        return effectiveOffset;
    }

    public void setEffectiveOffset(int effectiveOffset) {
        this.effectiveOffset = effectiveOffset;
    }

    public int getNextOffset() {
        return nextOffset;
    }

    public void setNextOffset(int nextOffset) {
        this.nextOffset = nextOffset;
    }

    public int getEffectivePage() {
        return effectivePage;
    }

    public void setEffectivePage(int effectivePage) {
        this.effectivePage = effectivePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

}
