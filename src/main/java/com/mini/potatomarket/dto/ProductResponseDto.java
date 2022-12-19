package com.mini.potatomarket.dto;

import com.mini.potatomarket.entity.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private Long id;
    private String title;
    private String content;
    private Long price;
    private String nickname;

    public ProductResponseDto(Product product, List<CommentResponseDto> commentResponseDtos){
        this.title =product.getTitle();
        this.content = product.getContent();
        this.price = product.getPrice();
        this.nickname = product.getNickname();
    }
    public ProductResponseDto(Product product){
        this.title =product.getTitle();
        this.content = product.getContent();
        this.price = product.getPrice();
    }
}
