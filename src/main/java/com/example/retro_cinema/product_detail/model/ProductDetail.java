package com.example.retro_cinema.product_detail.model;

import com.example.retro_cinema.products.model.Products;
import com.example.retro_cinema.screenings.model.Screenings;
import com.example.retro_cinema.user.model.AccountUser;
import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
@Table(name = "product_detail")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    @Column(columnDefinition = "boolean default true")
    private boolean flag;
    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Products products;
    @ManyToOne
    @JoinColumn(name = "screening_id", referencedColumnName = "id")
    private Screenings screenings;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountUser accountUser;

    public ProductDetail() {
    }

    public ProductDetail(int id, int quantity, boolean flag, Products products, Screenings screenings, AccountUser accountUser) {
        this.id = id;
        this.quantity = quantity;
        this.flag = flag;
        this.products = products;
        this.screenings = screenings;
        this.accountUser = accountUser;
    }

    public ProductDetail(int quantity, boolean flag, Products products, Screenings screenings, AccountUser accountUser) {
        this.quantity = quantity;
        this.flag = flag;
        this.products = products;
        this.screenings = screenings;
        this.accountUser = accountUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Screenings getScreenings() {
        return screenings;
    }

    public void setScreenings(Screenings screenings) {
        this.screenings = screenings;
    }

    public AccountUser getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUser accountUser) {
        this.accountUser = accountUser;
    }
}
