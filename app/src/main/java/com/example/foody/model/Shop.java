package com.example.foody.model;


import java.io.Serializable;

public class Shop implements Serializable {
    String shopName;
    String adress;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    String imgUrl;

    public Shop(String shopName, String adress, String imgUrl) {
        this.shopName = shopName;
        this.adress = adress;
        this.imgUrl = imgUrl;
    }
}
