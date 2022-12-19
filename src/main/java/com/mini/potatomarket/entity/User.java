package com.mini.potatomarket.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
//    @Column
//    private Product product;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
//    @Column
//    private Comment comment;

    public User(String loginId, String password, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
    }
}