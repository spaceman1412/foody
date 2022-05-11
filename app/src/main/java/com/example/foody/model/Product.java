package com.example.foody.model;

public class Product {
    String productId;
    String productName;
    String price;
    String imageItem;
    String shopId;
    String cartId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getImageItem() {
        return imageItem;
    }

    public void setImageItem(String imageItem) {
        this.imageItem = imageItem;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Product(String productId, String productName, String price, String imageItem, String shopId) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.imageItem = imageItem;
        this.shopId = shopId;

    }
}
