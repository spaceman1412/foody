package com.example.foody.model;

import java.util.List;

public class CartItem {
    String cartID;
    List<Product> products;
    String status;

    public CartItem(String cartID, List<Product> products, String status) {
        this.cartID = cartID;
        this.products = products;
        this.status = status;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
