package com.example.retro_cinema.user.dto;


import com.example.retro_cinema.user.model.Roles;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AccountUserDto implements Validator {

    private Integer id;
    @NotBlank(message = "Email could not be void!")
    @Email
    private String email;
    @NotBlank(message = "Password could not be void!")
    @Size(min = 6, max = 30, message = "Password must be at least 6 character!")
    private String pass;
    private Roles roles;
    private boolean flag;

    public AccountUserDto() {
    }

    public AccountUserDto(Integer id, String email, String pass, Roles roles, boolean flag) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.roles = roles;
        this.flag = flag;
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

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
