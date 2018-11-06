
package com.example.alex.testapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Serializable
{

    @SerializedName("listing_id")
    @Expose
    private int listingId;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("category_id")
    @Expose
    private int categoryId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("creation_tsz")
    @Expose
    private int creationTsz;
    @SerializedName("ending_tsz")
    @Expose
    private int endingTsz;
    @SerializedName("original_creation_tsz")
    @Expose
    private int originalCreationTsz;
    @SerializedName("last_modified_tsz")
    @Expose
    private int lastModifiedTsz;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("sku")
    @Expose
    private List<Object> sku = new ArrayList<Object>();
    @SerializedName("tags")
    @Expose
    private List<String> tags = new ArrayList<String>();
    @SerializedName("category_path")
    @Expose
    private List<String> categoryPath = new ArrayList<String>();
    @SerializedName("category_path_ids")
    @Expose
    private List<Integer> categoryPathIds = new ArrayList<Integer>();
    @SerializedName("materials")
    @Expose
    private List<String> materials = new ArrayList<String>();
    @SerializedName("shop_section_id")
    @Expose
    private int shopSectionId;
    @SerializedName("featured_rank")
    @Expose
    private Object featuredRank;
    @SerializedName("state_tsz")
    @Expose
    private int stateTsz;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("views")
    @Expose
    private int views;
    @SerializedName("num_favorers")
    @Expose
    private int numFavorers;
    @SerializedName("shipping_template_id")
    @Expose
    private int shippingTemplateId;
    @SerializedName("processing_min")
    @Expose
    private int processingMin;
    @SerializedName("processing_max")
    @Expose
    private int processingMax;
    @SerializedName("who_made")
    @Expose
    private String whoMade;
    @SerializedName("is_supply")
    @Expose
    private String isSupply;
    @SerializedName("when_made")
    @Expose
    private String whenMade;
    @SerializedName("item_weight")
    @Expose
    private Object itemWeight;
    @SerializedName("item_weight_unit")
    @Expose
    private String itemWeightUnit;
    @SerializedName("item_length")
    @Expose
    private Object itemLength;
    @SerializedName("item_width")
    @Expose
    private Object itemWidth;
    @SerializedName("item_height")
    @Expose
    private Object itemHeight;
    @SerializedName("item_dimensions_unit")
    @Expose
    private String itemDimensionsUnit;
    @SerializedName("is_private")
    @Expose
    private boolean isPrivate;
    @SerializedName("recipient")
    @Expose
    private Object recipient;
    @SerializedName("occasion")
    @Expose
    private Object occasion;
    @SerializedName("style")
    @Expose
    private Object style;
    @SerializedName("non_taxable")
    @Expose
    private boolean nonTaxable;
    @SerializedName("is_customizable")
    @Expose
    private boolean isCustomizable;
    @SerializedName("is_digital")
    @Expose
    private boolean isDigital;
    @SerializedName("file_data")
    @Expose
    private String fileData;
    @SerializedName("should_auto_renew")
    @Expose
    private boolean shouldAutoRenew;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("has_variations")
    @Expose
    private boolean hasVariations;
    @SerializedName("taxonomy_id")
    @Expose
    private int taxonomyId;
    @SerializedName("taxonomy_path")
    @Expose
    private List<String> taxonomyPath = new ArrayList<String>();
    @SerializedName("used_manufacturer")
    @Expose
    private boolean usedManufacturer;
    private final static long serialVersionUID = -6999482512599715687L;

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreationTsz() {
        return creationTsz;
    }

    public void setCreationTsz(int creationTsz) {
        this.creationTsz = creationTsz;
    }

    public int getEndingTsz() {
        return endingTsz;
    }

    public void setEndingTsz(int endingTsz) {
        this.endingTsz = endingTsz;
    }

    public int getOriginalCreationTsz() {
        return originalCreationTsz;
    }

    public void setOriginalCreationTsz(int originalCreationTsz) {
        this.originalCreationTsz = originalCreationTsz;
    }

    public int getLastModifiedTsz() {
        return lastModifiedTsz;
    }

    public void setLastModifiedTsz(int lastModifiedTsz) {
        this.lastModifiedTsz = lastModifiedTsz;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Object> getSku() {
        return sku;
    }

    public void setSku(List<Object> sku) {
        this.sku = sku;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(List<String> categoryPath) {
        this.categoryPath = categoryPath;
    }

    public List<Integer> getCategoryPathIds() {
        return categoryPathIds;
    }

    public void setCategoryPathIds(List<Integer> categoryPathIds) {
        this.categoryPathIds = categoryPathIds;
    }

    public List<String> getMaterials() {
        return materials;
    }

    public void setMaterials(List<String> materials) {
        this.materials = materials;
    }

    public int getShopSectionId() {
        return shopSectionId;
    }

    public void setShopSectionId(int shopSectionId) {
        this.shopSectionId = shopSectionId;
    }

    public Object getFeaturedRank() {
        return featuredRank;
    }

    public void setFeaturedRank(Object featuredRank) {
        this.featuredRank = featuredRank;
    }

    public int getStateTsz() {
        return stateTsz;
    }

    public void setStateTsz(int stateTsz) {
        this.stateTsz = stateTsz;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getNumFavorers() {
        return numFavorers;
    }

    public void setNumFavorers(int numFavorers) {
        this.numFavorers = numFavorers;
    }

    public int getShippingTemplateId() {
        return shippingTemplateId;
    }

    public void setShippingTemplateId(int shippingTemplateId) {
        this.shippingTemplateId = shippingTemplateId;
    }

    public int getProcessingMin() {
        return processingMin;
    }

    public void setProcessingMin(int processingMin) {
        this.processingMin = processingMin;
    }

    public int getProcessingMax() {
        return processingMax;
    }

    public void setProcessingMax(int processingMax) {
        this.processingMax = processingMax;
    }

    public String getWhoMade() {
        return whoMade;
    }

    public void setWhoMade(String whoMade) {
        this.whoMade = whoMade;
    }

    public String getIsSupply() {
        return isSupply;
    }

    public void setIsSupply(String isSupply) {
        this.isSupply = isSupply;
    }

    public String getWhenMade() {
        return whenMade;
    }

    public void setWhenMade(String whenMade) {
        this.whenMade = whenMade;
    }

    public Object getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(Object itemWeight) {
        this.itemWeight = itemWeight;
    }

    public String getItemWeightUnit() {
        return itemWeightUnit;
    }

    public void setItemWeightUnit(String itemWeightUnit) {
        this.itemWeightUnit = itemWeightUnit;
    }

    public Object getItemLength() {
        return itemLength;
    }

    public void setItemLength(Object itemLength) {
        this.itemLength = itemLength;
    }

    public Object getItemWidth() {
        return itemWidth;
    }

    public void setItemWidth(Object itemWidth) {
        this.itemWidth = itemWidth;
    }

    public Object getItemHeight() {
        return itemHeight;
    }

    public void setItemHeight(Object itemHeight) {
        this.itemHeight = itemHeight;
    }

    public String getItemDimensionsUnit() {
        return itemDimensionsUnit;
    }

    public void setItemDimensionsUnit(String itemDimensionsUnit) {
        this.itemDimensionsUnit = itemDimensionsUnit;
    }

    public boolean isIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Object getRecipient() {
        return recipient;
    }

    public void setRecipient(Object recipient) {
        this.recipient = recipient;
    }

    public Object getOccasion() {
        return occasion;
    }

    public void setOccasion(Object occasion) {
        this.occasion = occasion;
    }

    public Object getStyle() {
        return style;
    }

    public void setStyle(Object style) {
        this.style = style;
    }

    public boolean isNonTaxable() {
        return nonTaxable;
    }

    public void setNonTaxable(boolean nonTaxable) {
        this.nonTaxable = nonTaxable;
    }

    public boolean isIsCustomizable() {
        return isCustomizable;
    }

    public void setIsCustomizable(boolean isCustomizable) {
        this.isCustomizable = isCustomizable;
    }

    public boolean isIsDigital() {
        return isDigital;
    }

    public void setIsDigital(boolean isDigital) {
        this.isDigital = isDigital;
    }

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }

    public boolean isShouldAutoRenew() {
        return shouldAutoRenew;
    }

    public void setShouldAutoRenew(boolean shouldAutoRenew) {
        this.shouldAutoRenew = shouldAutoRenew;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isHasVariations() {
        return hasVariations;
    }

    public void setHasVariations(boolean hasVariations) {
        this.hasVariations = hasVariations;
    }

    public int getTaxonomyId() {
        return taxonomyId;
    }

    public void setTaxonomyId(int taxonomyId) {
        this.taxonomyId = taxonomyId;
    }

    public List<String> getTaxonomyPath() {
        return taxonomyPath;
    }

    public void setTaxonomyPath(List<String> taxonomyPath) {
        this.taxonomyPath = taxonomyPath;
    }

    public boolean isUsedManufacturer() {
        return usedManufacturer;
    }

    public void setUsedManufacturer(boolean usedManufacturer) {
        this.usedManufacturer = usedManufacturer;
    }

}
