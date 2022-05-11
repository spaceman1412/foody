package com.example.foody.model;

import java.util.List;

public class CartItem {


    String cartID;
    List<Product> products;

    String shopName;
    String userId;

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CartItem(String cartID, List<Product> products, String shopName, String userId) {
        this.cartID = cartID;
        this.products = products;
        this.shopName = shopName;
        this.userId = userId;
    }
}
