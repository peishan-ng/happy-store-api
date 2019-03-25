package com.happy.store.api.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Media implements Serializable {
	public enum Type { IMAGE, VIDEO }
	
	private int prdtMediaId;
	private int productId;
	private String filename;
	private int ordering;
	private Type mediaType;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	public int getPrdtMediaId() {
		return prdtMediaId;
	}
	public void setPrdtMediaId(int prdtMediaId) {
		this.prdtMediaId = prdtMediaId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getOrdering() {
		return ordering;
	}
	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}
	public Type getMediaType() {
		return mediaType;
	}
	public void setMediaType(Type mediaType) {
		this.mediaType = mediaType;
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
