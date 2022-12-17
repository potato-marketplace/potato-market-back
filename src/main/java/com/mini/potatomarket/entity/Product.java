package com.mini.potatomarket.entity;

import com.mini.potatomarket.dto.ProductResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

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

    @OneToMany(mappedBy = "PRODUCT")
    private List<Comment>commentList = new ArrayList;



    public Product(ProductResponseDto productResponseDto, User user){
        this.user = user;
        this.title = productResponseDto.getTitle();
        this.price = productResponseDto.getPrice();
        this.content = productResponseDto.getContent();
        this.nickname = productResponseDto.getNickname();
    }

    public void update(ProductResponseDto productResponseDto){
        this.title= productResponseDto.getTitle();
        this.price = productResponseDto.getPrice();
        this.content = productResponseDto.getContent();
    }
}
