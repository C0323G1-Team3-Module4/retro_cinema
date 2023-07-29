package com.example.retro_cinema.user.model;

import com.example.retro_cinema.customer.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class AccountUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    @JsonIgnore
    private String pass;
    @Column(columnDefinition = "bit default 1")
    private boolean flag;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Roles roles;

    @OneToMany(mappedBy = "accountUser")
    private Set<Customer> customerSet;

    public AccountUser() {
    }

    public AccountUser(Integer id, String email, String pass, boolean flag, Roles roles, Set<Customer> customerSet) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.flag = flag;
        this.roles = roles;
        this.customerSet = customerSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Set<Customer> getCustomerSet() {
        return customerSet;
    }

    public void setCustomerSet(Set<Customer> customerSet) {
        this.customerSet = customerSet;
    }
}
