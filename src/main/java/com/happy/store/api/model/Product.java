package com.happy.store.api.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product implements Serializable {
	public enum Language { en_US }
	
	@JsonProperty("product_id")
	private int productId;
	@JsonProperty("sku")
	private String sku;
	@JsonProperty("title")
	private String title;
	@JsonProperty("brand_code")
	private String brandCode;
	@JsonProperty("brand_name")
	private String brandName;
	@JsonProperty("description")
	private String description;
	@JsonProperty("how_to_use")
	private String howToUse;
	@JsonProperty("ingredients")
	private String ingredients;
	@JsonProperty("language_code")
	private Language languageCode;
	@JsonProperty("category_id")
	private int categoryId;
	@JsonProperty("currency_code")
	private String currencyCode;
	@JsonProperty("retail_price")
	private double retailPrice;
	@JsonProperty("selling_price")
	private double sellingPrice;
	@JsonProperty("qty")
	private int qty;
	@JsonProperty("created_at")
	private Timestamp createdAt;
	@JsonProperty("updated_at")
	private Timestamp updatedAt;
	
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHowToUse() {
		return howToUse;
	}
	public void setHowToUse(String howToUse) {
		this.howToUse = howToUse;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	public Language getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(Language languageCode) {
		this.languageCode = languageCode;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public double getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
