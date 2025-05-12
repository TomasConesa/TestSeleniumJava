package org.example.model;

public class Article {

    private String description;
    private double salePrice;
    private int stock;


    public Article(String description, double salePrice, int stock) {
        this.description = description;
        this.salePrice = salePrice;
        this.stock = stock;
    }

    public boolean thereIsStock(){return stock > 0;}

    public double checkPrice(){return salePrice;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
