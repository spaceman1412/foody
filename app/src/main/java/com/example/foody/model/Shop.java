package com.example.foody.model;


import java.io.Serializable;

public class Shop implements Serializable {
    public Shop(int id,String shopName, String adress,  String imgUrl) {
        this.shopName = shopName;
        this.adress = adress;
        this.id = id;
        this.imgUrl = imgUrl;
    }

    String shopName;
    String adress;
    int id;



    String imgUrl;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
