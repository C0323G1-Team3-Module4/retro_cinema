package com.example.retro_cinema.customer.dto;

import com.example.retro_cinema.user.dto.AccountUserDto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;

import javax.validation.constraints.NotNull;

import java.time.format.DateTimeParseException;
import java.util.Date;

public class CustomerDto implements Validator {

    private Integer id;
    private String fullName;
    private String phone;
    private String address;
    private String gender;
    private String dob;
    private String image;
    private boolean enabled;
    private Date expiryDate;
    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    private AccountUserDto accountUserDto;

    public CustomerDto() {
    }

    public CustomerDto(Integer id, String fullName, String phone, String address, String gender, String dob, String image, boolean enabled, Date expiryDate, String verificationCode, AccountUserDto accountUserDto) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.dob = dob;
        this.image = image;
        this.enabled = enabled;
        this.expiryDate = expiryDate;
        this.verificationCode = verificationCode;
        this.accountUserDto = accountUserDto;
    }

    public CustomerDto(Integer id, String fullName, String phone, String address, String gender, String dob, String image, AccountUserDto accountUserDto) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.dob = dob;
        this.image = image;
        this.accountUserDto = accountUserDto;
    }

    public CustomerDto(Integer id, String fullName, String phone, String address, String gender, String dob, String image) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.dob = dob;
        this.image = image;
    }

    public CustomerDto(String fullName) {
        this.fullName = fullName;
    }

    public CustomerDto(String fullName, AccountUserDto accountUserDto) {
        this.fullName = fullName;
        this.accountUserDto = accountUserDto;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public AccountUserDto getAccountUserDto() {
        return accountUserDto;
    }

    public void setAccountUserDto(AccountUserDto accountUserDto) {
        this.accountUserDto = accountUserDto;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerDto customerDto = (CustomerDto) target;
        if (customerDto.getFullName().equals("")) {
            errors.rejectValue("fullName", "", "Name could not be void!");
        } else if (customerDto.getFullName().length() > 50) {
            errors.rejectValue("fullName", "", "Full name should not exceed 50 characters");
        } else if (customerDto.getFullName().length() < 2) {
            errors.rejectValue("fullName", "", "Full name must be more than 2 characters");
        }
        if (customerDto.getPhone().equals("")) {
            errors.rejectValue("phone", "", "Phone could not be void!");
        } else if (!customerDto.getPhone().matches("^0\\d{9}$")) {
            errors.rejectValue("phone", "", "Phone number is not in the correct format or the number is not enough");
        }
        if (customerDto.getAddress().equals("")) {
            errors.rejectValue("address", "", "Address could not be void!");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate age = LocalDate.parse(customerDto.getDob(), formatter);
            LocalDate now = LocalDate.now();
            int yearOld = Period.between(age, now).getYears();
            if (yearOld < 1 || yearOld > 100) {
                errors.rejectValue("dob", "", "Wrong date!");
            }
        } catch (DateTimeParseException e) {
            errors.rejectValue("dob", "", "Age is not in the correct format!");
        }
    }
}
