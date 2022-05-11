package com.example.foody.model;

public class SingletonLogin {
    private static SingletonLogin INSTANCE = new SingletonLogin();

    // other instance variables can be here

    public static boolean logined = false;
    public static String userId = "";

    private SingletonLogin() {};

    public static SingletonLogin getInstance() {
        return(INSTANCE);
    }

    // other instance methods can follow
}
