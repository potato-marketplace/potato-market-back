package com.mini.potatomarket.repository;

import com.mini.potatomarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product ,Long> {

    List<Product> findAllByOrderByCreatedAtDesc();

}
