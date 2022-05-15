package com.example.foody.model;

public class ProductAmount {
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    Product product;
    int amount;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ProductAmount(String id,int amount,Product product) {
        this.product = product;
        this.amount = amount;
        this.id = id;
    }
}
