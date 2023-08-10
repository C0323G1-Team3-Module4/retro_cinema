package com.example.retro_cinema.user.dto;


import com.example.retro_cinema.customer.model.Customer;
import com.example.retro_cinema.user.model.Roles;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.*;
import java.util.Date;


public class AccountUserDto implements Validator {

    private Integer id;
    private String username;
    private String email;
    private String pass;
    private Roles roles;
    private Date expiryDate;
    private String verificationCode;
    private Customer customer;
    private boolean flag;

    public AccountUserDto() {
    }

    public AccountUserDto(String username, String email, String pass, Roles roles, Date expiryDate, String verificationCode) {
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.roles = roles;
        this.expiryDate = expiryDate;
        this.verificationCode = verificationCode;
    }

    public AccountUserDto(String username, String email, String pass, Date expiryDate, String verificationCode, boolean flag) {
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.expiryDate = expiryDate;
        this.verificationCode = verificationCode;
        this.flag = flag;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountUserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountUserDto accountUserDto = (AccountUserDto) target;
        if (accountUserDto.getUsername().trim().isEmpty()) {
            errors.rejectValue("username", "", "User name cound not be void!");
        } else if (accountUserDto.getUsername().length() > 5) {
            errors.rejectValue("username", "", "Your UserName must be at least 6 characters or more!");
        }
        String email = accountUserDto.getEmail();
        if (email == null || email.trim().isEmpty()) {
            errors.rejectValue("email", "", "Email cound not be void!");
        } else if (!isValidEmail(email)) {
            errors.rejectValue("email", "", "Invalid email format!");
        }
        String pass = accountUserDto.getPass();
        if (pass == null || pass.trim().isEmpty()) {
            errors.rejectValue("pass", "", "Password cound not be void!");
        } else if (!isValidPass(pass)) {
            errors.rejectValue("pass", "", "Password is malformed or less than 6 characters!");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    private boolean isValidPass(String pass) {
        String passRegex = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$";
        return pass.matches(passRegex);
    }
}

