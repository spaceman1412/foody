package com.example.foody.model;

import java.util.List;

public class CartItem {
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

    String cartID;
    List<Product> products;

    public CartItem(String cartID, List<Product> products, Shop shop) {
        this.cartID = cartID;
        this.products = products;
        this.shop = shop;
    }

    Shop shop;


}
