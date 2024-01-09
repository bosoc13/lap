package com.lap.webadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lap.common.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
