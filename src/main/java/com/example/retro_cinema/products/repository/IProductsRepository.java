package com.example.retro_cinema.products.repository;

import com.example.retro_cinema.products.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductsRepository extends JpaRepository<Products,Integer> {
    Page<Products> findAllByFlag(Pageable pageable,boolean flag);
}
