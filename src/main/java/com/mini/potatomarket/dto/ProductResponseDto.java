package com.mini.potatomarket.dto;

import com.mini.potatomarket.entity.Product;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentDtoList = new ArrayList<>();        //댓글 리스트

    public ProductResponseDto(Product product, List<CommentResponseDto> commentResponseDtos){
        this.id = product.getId();
        this.title =product.getTitle();
        this.content = product.getContent();
        this.price = product.getPrice();
        this.nickname = product.getUser().getNickname();        //nickname 유저에서 받아오기
        this.createdAt = product.getCreatedAt();
        this.modifiedAt = product.getModifiedAt();
        this.commentDtoList = commentResponseDtos;              //댓글 리스트 받아오기
    }
    public ProductResponseDto(Product product){
        this.id = product.getId();
        this.title =product.getTitle();
        this.content = product.getContent();
        this.price = product.getPrice();
        this.nickname = product.getUser().getNickname();        //nickname 유저에서 받아오기
        this.createdAt = product.getCreatedAt();
        this.modifiedAt = product.getModifiedAt();              //댓글 리스트 받아오기
    }
}
