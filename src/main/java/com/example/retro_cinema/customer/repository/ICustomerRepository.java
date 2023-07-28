package com.example.retro_cinema.customer.repository;

import com.example.retro_cinema.customer.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "select * from Customers where full_name like CONCAT('%', :name,'%') ",nativeQuery = true)
    Page<Customer> findByCustomers(@Param("name") String fullName, Pageable pageable);
    Optional<Customer> findById(Integer id);
    Customer findCustomerByAccountUser_Id(Integer id);
    Customer findByAccountUser_Email(String email);
    Customer findByVerificationCode(String code);
}
