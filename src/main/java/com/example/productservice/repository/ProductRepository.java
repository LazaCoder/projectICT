package com.example.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.productservice.model.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
    List<Product> findByTitleContainingIgnoreCase(String name);
}