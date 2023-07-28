package com.example.retro_cinema.customer.dto;

import com.example.retro_cinema.user.dto.AccountUserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class CustomerDto implements Validator {

    private Integer id;
    @NotBlank(message = "Name could not be void!")
    @Size(min = 2, max = 50)
    private String fullName;
    @NotBlank(message = "Phone could not be void!")
    @Size(min = 10, max = 12)
    private String phone;
    @NotBlank(message = "Address could not be void!")
    private String address;
    private String gender;
    @NotNull(message = "Date of birth could not be void!")
    private String dob;
    private boolean enabled;
    private Date expiryDate;
    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    private AccountUserDto accountUserDto;

    public CustomerDto() {
    }

    public CustomerDto(Integer id, String fullName, String phone, String address, String gender, String dob, boolean enabled, Date expiryDate, String verificationCode, AccountUserDto accountUserDto) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.dob = dob;
        this.enabled = enabled;
        this.expiryDate = expiryDate;
        this.verificationCode = verificationCode;
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

    }
}
