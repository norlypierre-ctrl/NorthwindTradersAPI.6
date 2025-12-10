package com.pluralsight.NorthwindTradersAPI6.models;

public class Product {
    private int productID;


    public Product(int productId) {
        this.productID = productId;

    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productID +
                '}';
    }
}

