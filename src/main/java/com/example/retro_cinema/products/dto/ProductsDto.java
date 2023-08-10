package com.example.retro_cinema.products.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductsDto implements Validator {
    private int id;
    private String productName;
    @NotNull(message = "Price is required")
    private double price;
    private boolean flag;
    private String img;

    public ProductsDto() {
    }

    public ProductsDto(int id, String productName, double price, boolean flag, String img) {
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

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductsDto productsDto = (ProductsDto) target;
        if(productsDto.getProductName().equals("")){
            errors.rejectValue("productName",null,"Product name cannot empty!");
        } else if (!productsDto.getProductName().matches("^[a-zA-Z0-9]+([\\\\t]+[a-zA-Z0-9]+)*$")) {
            errors.rejectValue("productName",null,"Product name no special characters");
        }

    }
}
