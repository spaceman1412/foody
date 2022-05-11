package com.example.foody.model;

import java.util.List;

public class CartItem {


    String cartID;
    List<Product> products;

    Shop shop;
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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CartItem(String cartID, List<Product> products, Shop shop, String userId) {
        this.cartID = cartID;
        this.products = products;
        this.shop = shop;
        this.userId = userId;
    }
}
