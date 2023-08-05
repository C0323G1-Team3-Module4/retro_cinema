package com.example.retro_cinema.customer.model;

import com.example.retro_cinema.user.model.AccountUser;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String phone;
    private String address;
    private String gender;
    private String dob;
    @Column(columnDefinition = "bit default 1")
    private boolean flag;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountUser accountUser;

    public Customer() {
    }

    public Customer(AccountUser accountUser) {
        this.accountUser = accountUser;
    }

    public Customer(Integer id, String fullName, String phone, String address, String gender, String dob, boolean flag, AccountUser accountUser) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.dob = dob;
        this.flag = flag;
        this.accountUser = accountUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public AccountUser getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUser accountUser) {
        this.accountUser = accountUser;
    }
}
