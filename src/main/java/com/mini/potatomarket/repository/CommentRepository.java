package com.mini.potatomarket.repository;

import com.mini.potatomarket.entity.Comment;
import com.mini.potatomarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByProduct(Product product);

    @Transactional
    void deleteAllByProduct(Product product);

    Comment findByProductAndId(Product product, Long id);
}
