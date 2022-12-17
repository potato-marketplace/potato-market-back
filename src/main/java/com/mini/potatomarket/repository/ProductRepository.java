package com.mini.potatomarket.repository;

import com.mini.potatomarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product ,Long> {

    List<Product> findAllByOrderByModifiedAtDesc();


    Optional<Product> findByIdAndUserId(Long id, String username);

    /*List<Product> findAllByCategory(String category);*/
}
