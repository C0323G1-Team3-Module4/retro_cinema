package com.example.retro_cinema.customer.repository;

import com.example.retro_cinema.customer.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
//    @Query(value = "select * from Customers where full_name like CONCAT('%', :searchByName,'%') ",nativeQuery = true)
//   Page<Customer> findAll(@Param("searchByName") String name, Pageable pageable,boolean flag);
//
    @Query(value = "select * from customers where id = :id and flag = true ",nativeQuery = true)
    Optional<Customer> findById(Integer id);
    Customer findCustomerByAccountUser_Id(Integer id);
    Page<Customer> findCustomerByFullNameContainingAndFlag(String name, Pageable pageable, boolean flag);
    Customer findCustomerByAccountUser_Username(String name);
}
