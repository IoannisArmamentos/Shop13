package com.shop13.model;

public class Product {
	private String name, thumbnailUrl, price,  siteUrl, type;
	private int id;

	public Product() {
	}

	public Product(String name, String thumbnailUrl, String price, String siteUrl, int id, String type) {
		this.name = name;
		this.thumbnailUrl = thumbnailUrl;
		this.price = price;
		this.siteUrl = siteUrl;
		this.id = id;
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public String getPrice() {
		return price;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}


}
