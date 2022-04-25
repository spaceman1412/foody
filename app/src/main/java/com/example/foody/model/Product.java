package com.example.foody.model;

public class Product {
    String productId;
    String productName;
    String price;
    String imageItem;

    public Product(String productId, String productName, String price, String im) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;

        this.imageItem = im;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImageItem() {
        return imageItem;
    }

    public void setImageItem(String imageItem) {
        this.imageItem = imageItem;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString()  {
        return this.productName + " have price is: "+ this.price;
    }
}
