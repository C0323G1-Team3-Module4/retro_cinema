package com.example.retro_cinema.user.model;

import com.example.retro_cinema.customer.model.Customer;
import com.example.retro_cinema.seatDetails.model.SeatDetails;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class AccountUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String email;
    @JsonIgnore
    private String pass;
    @Column(columnDefinition = "bit default 1")
    private boolean flag;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Roles roles;
    private Date expiryDate;
    @Column(name = "verification_code", length = 64)
    private String verificationCode;
    private boolean enabled;

    @OneToMany(mappedBy = "accountUser")
    @JsonBackReference
    private Set<Customer> customerSet;

    @OneToMany(mappedBy = "accountUser")
    @JsonBackReference
    private Set<SeatDetails> seatDetails;

    public AccountUser() {
    }

    public Set<SeatDetails> getSeatDetails() {
        return seatDetails;
    }

    public void setSeatDetails(Set<SeatDetails> seatDetails) {
        this.seatDetails = seatDetails;
    }

    public AccountUser(Integer id, String username, String email, String pass, boolean flag, Roles roles, Date expiryDate, String verificationCode, boolean enabled, Set<Customer> customerSet, Set<SeatDetails> seatDetails) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.flag = flag;
        this.roles = roles;
        this.expiryDate = expiryDate;
        this.verificationCode = verificationCode;
        this.enabled = enabled;
        this.customerSet = customerSet;
        this.seatDetails = seatDetails;
    }

    public AccountUser(Integer id, String username, String email, String pass, boolean flag, Roles roles, Date expiryDate, String verificationCode) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.flag = flag;
        this.roles = roles;
        this.expiryDate = expiryDate;
        this.verificationCode = verificationCode;
    }

    public AccountUser(String username, String email, String pass, boolean flag, Roles roles, Date expiryDate, String verificationCode) {
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.flag = flag;
        this.roles = roles;
        this.expiryDate = expiryDate;
        this.verificationCode = verificationCode;
    }

    public AccountUser(String username, String email, String pass, Roles roles, Date expiryDate, String verificationCode) {
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.roles = roles;
        this.expiryDate = expiryDate;
        this.verificationCode = verificationCode;
    }

    public AccountUser(String username, String email, String pass, Date expiryDate, String verificationCode) {
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.expiryDate = expiryDate;
        this.verificationCode = verificationCode;
    }

    public AccountUser(String username, String email, String pass, boolean flag, Roles roles, Date expiryDate, String verificationCode, boolean enabled) {
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.flag = flag;
        this.roles = roles;
        this.expiryDate = expiryDate;
        this.verificationCode = verificationCode;
        this.enabled = enabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Set<Customer> getCustomerSet() {
        return customerSet;
    }

    public void setCustomerSet(Set<Customer> customerSet) {
        this.customerSet = customerSet;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
