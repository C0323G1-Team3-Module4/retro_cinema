package com.example.retro_cinema.user.dto;

import java.util.Date;

public class UserDto {
    private String fullName;
    private Date expiryDate;
    private AccountUserDto accountUserDto;

    public UserDto() {
    }

    public UserDto(String fullName, Date expiryDate, AccountUserDto accountUserDto) {
        this.fullName = fullName;
        this.expiryDate = expiryDate;
        this.accountUserDto = accountUserDto;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public AccountUserDto getAccountUserDto() {
        return accountUserDto;
    }

    public void setAccountUserDto(AccountUserDto accountUserDto) {
        this.accountUserDto = accountUserDto;
    }
}
