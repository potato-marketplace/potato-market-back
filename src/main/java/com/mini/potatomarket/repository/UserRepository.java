package com.mini.potatomarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mini.potatomarket.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);
    Optional<User> findByNickname(String nickname);
    boolean existsByNickname(String nickname);

}