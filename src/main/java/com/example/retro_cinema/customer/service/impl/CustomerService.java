package com.example.retro_cinema.customer.service.impl;

import com.example.retro_cinema.customer.model.Customer;
import com.example.retro_cinema.customer.repository.ICustomerRepository;
import com.example.retro_cinema.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
@Lazy
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository iCustomerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<Customer> findAll(String searchByName, Pageable pageable) {
        return iCustomerRepository.findAll(searchByName, pageable);
    }

    @Override
    public Customer findByIdCustomer(Integer id) {
        return iCustomerRepository.findById(id).get();
    }

    @Override
    public void create(Customer customer) {
        iCustomerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        Customer oldCustomer = iCustomerRepository.findById(customer.getId()).get();
        oldCustomer.setImage(customer.getImage());
        oldCustomer.setFullName(customer.getFullName());
        oldCustomer.setAddress(customer.getAddress());
        oldCustomer.setGender(customer.getGender());
        oldCustomer.setPhone(customer.getPhone());
        oldCustomer.setDob(customer.getDob());
        oldCustomer.setImage(customer.getImage());
        iCustomerRepository.save(oldCustomer);
    }

    @Override
    public void delete(Integer id) {
        Customer customer = findById(id);
        customer.setFlag(false);
        iCustomerRepository.save(customer);
    }

    @Override
    public Customer findByIdAccount(Integer id) {
        if (iCustomerRepository.findCustomerByAccountUser_Id(id) != null) {
            return iCustomerRepository.findCustomerByAccountUser_Id(id);
        } else {
            return null;
        }

    }


    @Override
    public Customer findById(int id) {
        return iCustomerRepository.findById(id).get();
    }

}
