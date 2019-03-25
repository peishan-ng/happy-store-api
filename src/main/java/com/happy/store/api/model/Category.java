package com.happy.store.api.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Category implements Serializable {
	private int categoryId;
	private int newArrival;
	private int sale;
	private String lvl1Code;
	private String lvl1Alias;
	private String lvl2Code;
	private String lvl2Alias;
	private String lvl3Code;
	private String lvl3Alias;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getNewArrival() {
		return newArrival;
	}
	public void setNewArrival(int newArrival) {
		this.newArrival = newArrival;
	}
	public int getSale() {
		return sale;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
	public String getLvl1Code() {
		return lvl1Code;
	}
	public void setLvl1Code(String lvl1Code) {
		this.lvl1Code = lvl1Code;
	}
	public String getLvl1Alias() {
		return lvl1Alias;
	}
	public void setLvl1Alias(String lvl1Alias) {
		this.lvl1Alias = lvl1Alias;
	}
	public String getLvl2Code() {
		return lvl2Code;
	}
	public void setLvl2Code(String lvl2Code) {
		this.lvl2Code = lvl2Code;
	}
	public String getLvl2Alias() {
		return lvl2Alias;
	}
	public void setLvl2Alias(String lvl2Alias) {
		this.lvl2Alias = lvl2Alias;
	}
	public String getLvl3Code() {
		return lvl3Code;
	}
	public void setLvl3Code(String lvl3Code) {
		this.lvl3Code = lvl3Code;
	}
	public String getLvl3Alias() {
		return lvl3Alias;
	}
	public void setLvl3Alias(String lvl3Alias) {
		this.lvl3Alias = lvl3Alias;
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
