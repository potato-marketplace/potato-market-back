package com.mini.potatomarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mini.potatomarket.dto.CommentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends Timestamped{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;                // 댓글 번호

    @Column(nullable = false)
    private String nickname;        // 작성자명

    @Column(nullable = false)
    private String contents;        // 작성내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "userid", nullable = false)
    private User user;              // 작성자 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid", nullable = false)
    @JsonIgnore
    private Product product;        // 게시판 id

    @Column(nullable = false)
    private int depth;             // 대댓글 확인용

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "parentid")
    private Comment parent;         // 부모 댓글

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", orphanRemoval = true)
    private List<Comment> children = new ArrayList<>();  // 대댓글 리스트



    public Comment(CommentRequestDto requestDto, String nickname, Product product, User user){
        this.contents      =   requestDto.getContent();        // 댓글 내용
        this.nickname      =   nickname;                       // 작성자 닉네임
        this.user          =   user;                           // User FK
        this.product       =   product;                        // Product FK
        this.depth         =   0;                              // 댓글 뎁스
    }

    public Comment(CommentRequestDto requestDto, String nickname, Product product, User user, Comment comment){
        this.contents      =   requestDto.getContent();        // 댓글 내용
        this.nickname      =   nickname;                       // 작성자 닉네임
        this.user          =   user;                           // User FK
        this.product       =   product;                        // Product FK
        this.depth         =   1;                              // 댓글 뎁스
        this.parent        =   comment;
    }

    // 댓글 내용 업데이트 메소드
    public void update(CommentRequestDto requestDto){
        this.contents = requestDto.getContent();
    }
}