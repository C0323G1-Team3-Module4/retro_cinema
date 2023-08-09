package com.example.retro_cinema.customer.service;

import com.example.retro_cinema.customer.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface ICustomerService {
    Page<Customer> findAll(String searchByName, Pageable pageable,boolean flag);
    Customer findByIdCustomer(Integer id);
    void create(Customer customer);
    void update(Customer customer);
    void delete(Integer id);
    Customer findByIdAccount(Integer id);
    Customer findById(int id);
}
