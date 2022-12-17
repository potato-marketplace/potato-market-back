package com.mini.potatomarket.dto;

import com.mini.potatomarket.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter
public class CommentResponseDto extends CommentRequestDto {                   // 로그인 및 회원가입 응답 dto
    private Long id;                                                 // id
    private String content;                                          // 댓글 내용
    private String usernickname;                                     // 작성자명
    private Comment parent;                                            // 부모 번호
    private List<Comment> children;
    private int depth;                                              // 댓글 깊이
    private LocalDateTime createdAt;                                 // 작성시간
    private LocalDateTime modifiedAt;                                // 수정시간

    // Entity -> DTO
    public CommentResponseDto(Comment comment){
        this.id             = comment.getId();                      // 댓글 id
        this.usernickname   = comment.getUsernickname();            // 작성자명
        this.content        = comment.getContents();                // 작성내용
        this.parent         = comment.getParent();                  // 부모 댓글
        this.children       = comment.getChildren();                // 대댓글
        this.depth          = comment.getDepth();                   // 댓글 깊이
        this.createdAt      = comment.getCreatedAt();               // 작성시간
        this.modifiedAt     = comment.getModifiedAt();              // 수정시간
    }
}


