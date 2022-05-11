package com.example.foody.model;

import java.util.ArrayList;
import java.util.List;

public class SingletonLogin {
    private static SingletonLogin INSTANCE = new SingletonLogin();

    // other instance variables can be here

    public static boolean logined = false;
    public static String userId = "";

    public static List<Product> productList = new ArrayList<Product>();

    public static List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public static void setCartItemList(List<CartItem> cartItemList) {
        SingletonLogin.cartItemList = cartItemList;
    }

    public static List<CartItem> cartItemList = new ArrayList<CartItem>();

    public static Product product;

    public static Product getProduct() {
        return product;
    }

    public static void setProduct(Product product) {
        SingletonLogin.product = product;
    }

    public static List<Product> getProductList() {
        return productList;
    }

    public static void setProductList(List<Product> productList) {
        SingletonLogin.productList = productList;
    }

    private SingletonLogin() {};

    public static boolean isLogined() {
        return logined;
    }

    public static void setLogined(boolean logined) {
        SingletonLogin.logined = logined;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        SingletonLogin.userId = userId;
    }

    public static SingletonLogin getInstance() {
        return(INSTANCE);
    }

    // other instance methods can follow
}
