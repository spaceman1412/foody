package com.example.foody.model;

import java.util.List;

public class CheckOut {
    public CheckOut(String checkOutId,int price,List<ProductAmount> productAmountList) {
        this.checkOutId = checkOutId;
        this.price = price;
        this.productAmountList = productAmountList;
    }

    public String getCheckOutId() {
        return checkOutId;
    }

    public void setCheckOutId(String checkOutId) {
        this.checkOutId = checkOutId;
    }


    String checkOutId;
    List<ProductAmount> productAmountList;

    public List<ProductAmount> getProductAmountList() {
        return productAmountList;
    }

    public void setProductAmountList(List<ProductAmount> productAmountList) {
        this.productAmountList = productAmountList;
    }

    int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
