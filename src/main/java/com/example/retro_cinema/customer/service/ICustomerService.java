package com.example.retro_cinema.customer.service;

import com.example.retro_cinema.customer.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface ICustomerService {
    Page<Customer> findByCustomer(String fullName, Pageable pageable);
    Customer findByIdCustomer(Integer id);
    void create(Customer customer);
    void update(Customer customer);
    void delete(Integer id);
    Customer findByIdAccount(Integer id);
    Customer findByEmail(String email);
    void sendVerificationEmail(Customer customer, String siteURL) throws MessagingException, UnsupportedEncodingException;
    Customer findByCode(String code);
    boolean verify(String verificationCode);
    void reset(Customer customer);
    void sendVerificationReset(Customer customer, String siteURL) throws MessagingException, UnsupportedEncodingException;
    boolean verifyReset(String verificationCode);
    void reset_pw(Customer customer, String new_pw);
    Customer findById(int id);
}
