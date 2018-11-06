
package com.example.alex.testapp.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Params implements Serializable
{

    @SerializedName("limit")
    @Expose
    private int limit;
    @SerializedName("offset")
    @Expose
    private int offset;
    @SerializedName("page")
    @Expose
    private Object page;
    @SerializedName("keywords")
    @Expose
    private String keywords;
    @SerializedName("sort_on")
    @Expose
    private String sortOn;
    @SerializedName("sort_order")
    @Expose
    private String sortOrder;
    @SerializedName("min_price")
    @Expose
    private Object minPrice;
    @SerializedName("max_price")
    @Expose
    private Object maxPrice;
    @SerializedName("color")
    @Expose
    private Object color;
    @SerializedName("color_accuracy")
    @Expose
    private int colorAccuracy;
    @SerializedName("tags")
    @Expose
    private Object tags;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("location")
    @Expose
    private Object location;
    @SerializedName("lat")
    @Expose
    private Object lat;
    @SerializedName("lon")
    @Expose
    private Object lon;
    @SerializedName("region")
    @Expose
    private Object region;
    @SerializedName("geo_level")
    @Expose
    private String geoLevel;
    @SerializedName("accepts_gift_cards")
    @Expose
    private String acceptsGiftCards;
    @SerializedName("translate_keywords")
    @Expose
    private String translateKeywords;
    private final static long serialVersionUID = 9083254774964937918L;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSortOn() {
        return sortOn;
    }

    public void setSortOn(String sortOn) {
        this.sortOn = sortOn;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Object getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Object minPrice) {
        this.minPrice = minPrice;
    }

    public Object getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Object maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Object getColor() {
        return color;
    }

    public void setColor(Object color) {
        this.color = color;
    }

    public int getColorAccuracy() {
        return colorAccuracy;
    }

    public void setColorAccuracy(int colorAccuracy) {
        this.colorAccuracy = colorAccuracy;
    }

    public Object getTags() {
        return tags;
    }

    public void setTags(Object tags) {
        this.tags = tags;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public Object getLon() {
        return lon;
    }

    public void setLon(Object lon) {
        this.lon = lon;
    }

    public Object getRegion() {
        return region;
    }

    public void setRegion(Object region) {
        this.region = region;
    }

    public String getGeoLevel() {
        return geoLevel;
    }

    public void setGeoLevel(String geoLevel) {
        this.geoLevel = geoLevel;
    }

    public String getAcceptsGiftCards() {
        return acceptsGiftCards;
    }

    public void setAcceptsGiftCards(String acceptsGiftCards) {
        this.acceptsGiftCards = acceptsGiftCards;
    }

    public String getTranslateKeywords() {
        return translateKeywords;
    }

    public void setTranslateKeywords(String translateKeywords) {
        this.translateKeywords = translateKeywords;
    }

}
