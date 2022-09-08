package com.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.Entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{

}
