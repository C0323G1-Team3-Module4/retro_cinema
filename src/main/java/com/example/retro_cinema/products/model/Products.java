package com.example.retro_cinema.products.model;

import javax.persistence.*;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_name")
    private String productName;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private double price;
    @Column(columnDefinition = "Bit default '1'")
    private boolean flag;
    @Column(columnDefinition = "VARCHAR(255)")
    private String img;

    public Products() {
    }

    public Products(int id, String productName, double price, boolean flag, String img) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.flag = flag;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
