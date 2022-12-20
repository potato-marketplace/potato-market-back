package com.mini.potatomarket.dto;

import com.mini.potatomarket.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class CommentResponseDto extends CommentRequestDto {                   // 로그인 및 회원가입 응답 dto
    private Long id;                                                 // id
    private LocalDateTime createdAt;                                 // 작성시간
    private LocalDateTime modifiedAt;                                // 수정시간
    private String content;                                          // 댓글 내용
    private String nickname;                                     // 작성자명
    private Long parentId;
    private int depth;                                              // 댓글 깊이
    private List<CommentResponseDto> children= new ArrayList<>();

    // Entity -> DTO
    public CommentResponseDto(Comment comment,List<CommentResponseDto> commentResponseDtoList){
        this.id             = comment.getId();                      // 댓글 id
        this.nickname       = comment.getNickname();                // 작성자명
        this.content        = comment.getContents();                // 작성내용
        this.depth          = comment.getDepth();                   // 댓글 깊이
        this.createdAt      = comment.getCreatedAt();               // 작성시간
        this.modifiedAt     = comment.getModifiedAt();              // 수정시간
        this.children       = commentResponseDtoList;                // 대댓글
    }

    public CommentResponseDto(Comment comment, Long id){
        this.id             = comment.getId();                      // 댓글 id
        this.nickname       = comment.getNickname();                // 작성자명
        this.content        = comment.getContents();                // 작성내용
        this.parentId       = id;
        this.depth          = comment.getDepth();                   // 댓글 깊이
        this.createdAt      = comment.getCreatedAt();               // 작성시간
        this.modifiedAt     = comment.getModifiedAt();              // 수정시간
    }

    public CommentResponseDto(Comment comment){
        this.id             = comment.getId();                      // 댓글 id
        this.nickname       = comment.getNickname();                // 작성자명
        this.content        = comment.getContents();                // 작성내용
        this.depth          = comment.getDepth();                   // 댓글 깊이
        this.createdAt      = comment.getCreatedAt();               // 작성시간
        this.modifiedAt     = comment.getModifiedAt();              // 수정시간
    }

}


