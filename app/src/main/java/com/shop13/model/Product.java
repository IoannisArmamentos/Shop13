package com.shop13.model;

public class Product {
    private String name, thumbnailUrl, price, siteUrl, type, buyUrl, shipping, delivery;
    private int id;

    public Product() {
    }

    public Product(String name, String thumbnailUrl, String price, String siteUrl, int id, String type, String buyUrl, String shipping, String delivery) {
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
        this.price = price;
        this.siteUrl = siteUrl;
        this.id = id;
        this.type = type;
        this.buyUrl = buyUrl;
        this.shipping = shipping;
        this.delivery = delivery;
    }

    public String getBuyUrl() {
        return buyUrl;
    }

    public void setBuyUrl(String buyUrl) {
        this.buyUrl = buyUrl;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
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

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", price='" + price + '\'' +
                ", siteUrl='" + siteUrl + '\'' +
                ", type='" + type + '\'' +
                ", buyUrl='" + buyUrl + '\'' +
                ", shipping='" + shipping + '\'' +
                ", delivery='" + delivery + '\'' +
                ", id=" + id +
                '}';
    }
}
