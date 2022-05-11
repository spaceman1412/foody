package com.example.foody.model;

public class SingletonLogin {
    private static SingletonLogin INSTANCE = new SingletonLogin();

    // other instance variables can be here

    public static boolean logined = false;
    public static String userId = "";

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
