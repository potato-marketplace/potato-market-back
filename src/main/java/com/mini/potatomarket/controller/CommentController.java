package com.mini.potatomarket.controller;

import com.mini.potatomarket.dto.CommentRequestDto;
import com.mini.potatomarket.dto.CommentResponseDto;
import com.mini.potatomarket.service.CommentService;
import com.mini.potatomarket.util.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.OptionalLong;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}/{comment_id}") // 뒤쪽 comment_id는 있어도 되고 없어도 됨
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long id, @PathVariable(required = false) Optional<Long>comment_id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(commentService.createcomment(id,comment_id, requestDto, userDetails.getUser()));
    }

    // DB update (Comment)
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(commentService.updatecomment(id, requestDto, userDetails.getUser()));
    }

    // DB delete (Comment)
    @DeleteMapping("/{id}")
    public ResponseEntity<CommentResponseDto> deleteComment(@PathVariable  Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(commentService.deletecomment(id, userDetails.getUser()));
    }
}
