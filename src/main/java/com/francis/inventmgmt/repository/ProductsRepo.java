package com.francis.inventmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.francis.inventmgmt.model.Products;

public interface ProductsRepo extends JpaRepository<Products,Long> {
    
}
