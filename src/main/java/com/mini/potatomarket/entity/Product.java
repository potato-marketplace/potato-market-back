package com.mini.potatomarket.entity;

import com.mini.potatomarket.dto.ProductRequestDto;
import com.mini.potatomarket.dto.ProductResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private String title;
    @Column
    private Long price;
    @Column
    private String content;
    @Column
    private String nickname;

    @OneToMany(mappedBy = "product")
    private List<Comment> commentList = new ArrayList<>();



    public Product(ProductRequestDto productRequestDto, User user){
        this.user = user;
        this.title = productRequestDto.getTitle();
        this.price = productRequestDto.getPrice();
        this.content = productRequestDto.getContent();
        this.nickname = productRequestDto.getNickname();
    }

    public void update(ProductRequestDto productRequestDto){
        this.title= productRequestDto.getTitle();
        this.price = productRequestDto.getPrice();
        this.content = productRequestDto.getContent();
    }
}
